package nyonbot.command;

import nyonbot.Logic.Result;
import nyonbot.model.NyonException;
import nyonbot.model.Task;
import nyonbot.model.TaskList;

public class MarkCommand extends Command {
    TaskList list;

    public MarkCommand(String input, TaskList list) {
        super(input);
        this.list = list;
    }

    public Result execute() throws NyonException {
        String taskName = this.input.substring("mark ".length()).strip();
        if (taskName.isBlank()) {
            throw new NyonException("cannot mark without a description :(");
        }
        int idx;
        try {
            idx = Integer.parseInt(taskName) - 1;
            if (idx < 0 || idx >= list.size()) {
                throw new NyonException("index out of bounds...");
            }
            Task task = list.get(idx);
            task.completeTask();
            return new Result(String.format("Marked %s as completed", task));
        } catch (NumberFormatException e) {
            for (Task task : list) {
                if (task.isSameTask(taskName)) {
                    task.completeTask();
                    return new Result(String.format("Marked %s as completed", task));
                }
            }
            return new Result("couldn't find the task... did you spell it right?");
        }

    }
}
