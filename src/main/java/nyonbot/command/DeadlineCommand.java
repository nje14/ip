package nyonbot.command;


import java.time.LocalDateTime;


import nyonbot.Parser;
import nyonbot.Logic.Result;
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
     * Creates a deadline command with the associated raw input and TaskList
     * 
     * @param cmd the raw input
     * @param list the TaskList to add the task to
     */
    public DeadlineCommand(String cmd, TaskList list) {
        super(cmd);
        this.list = list;
    }

    /** {@inheritDoc} */
    @Override
    public Result execute() throws NyonException {
        String deadlineCmd = this.input;
        int deadlineIndex = "deadline ".length();
        int deadLineByIndex = deadlineCmd.indexOf(" /by ");
        if (deadLineByIndex != -1 && deadLineByIndex < deadlineIndex + 1) {
            throw new NyonException("cannot omit the description");
        }
        if (deadLineByIndex == -1) {
            throw new NyonException("use /by to specify the deadline");
        }
        if (deadLineByIndex + " /by ".length() > deadlineCmd.length()) {
            throw new NyonException("cannot omit the deadline date");
        }
        LocalDateTime deadlineTime = Parser.parseDate(deadlineCmd.substring(deadLineByIndex+" /by ".length()));
        if (deadlineTime == null) {
            throw new NyonException("enter the time in the following format: dd/MM/yy HHmm");
        }
        Task deadline = new Deadline(deadlineCmd.substring(deadlineIndex, deadLineByIndex), deadlineTime);
        list.add(deadline);
        return new Result(String.format("I've added this task: \n%s\nThere are %s tasks in your list", deadline, list.size()));
    }
}
