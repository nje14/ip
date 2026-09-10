package nyonbot.command;


import java.time.LocalDateTime;
import java.util.HashMap;

import nyonbot.Logic.Result;
import nyonbot.Parser;
import nyonbot.model.Deadline;
import nyonbot.model.NyonException;
import nyonbot.model.Task;
import nyonbot.model.TaskList;

/**
 * Adds a deadline event to the task list
 */
public class DeadlineCommand extends Command {
    private TaskList list;

    /**
     * Creates a deadline command with parsed arguments and a TaskList.
     * @param arguments parsed command arguments
     * @param list the TaskList to add the task to
     */
    public DeadlineCommand(HashMap<String, String> arguments, TaskList list) {
        super(arguments);
        this.list = list;
    }

    /** {@inheritDoc} */
    @Override
    public Result execute() throws NyonException {
        assert arguments != null;
        String description = arguments.getOrDefault(DESCRIPTION_KEY, null);
        String deadlineValue = arguments.getOrDefault("--by", null);
        if (description == null || description.isBlank()) {
            throw new NyonException("cannot omit the description");
        }
        if (!arguments.containsKey("--by")) {
            throw new NyonException("use --by to specify the deadline");
        }
        if (deadlineValue.isBlank()) {
            throw new NyonException("cannot omit the deadline date");
        }
        LocalDateTime deadlineTime = Parser.parseDate(deadlineValue);
        if (deadlineTime == null) {
            throw new NyonException("enter the time in the following format: dd/MM/yy HHmm");
        }
        Task deadline = new Deadline(description, deadlineTime);
        list.add(deadline);
        return new Result(String.format(
                "I've added this task: \n%s\nThere are %s tasks in your list",
                deadline, list.size()),
            false,
            true
        );
    }
}
