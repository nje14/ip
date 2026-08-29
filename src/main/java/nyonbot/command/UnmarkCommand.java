package nyonbot.command;


import nyonbot.Logic.Result;
import nyonbot.model.NyonException;
import nyonbot.model.Task;
import nyonbot.model.TaskList;

public class UnmarkCommand extends Command {
    TaskList list;

    public UnmarkCommand(String input, TaskList list) {
        super(input);
        this.list = list;
    }

    public Result execute() throws NyonException {
        String taskName = this.input.substring("unmark ".length()).strip();
        if (taskName.isBlank()) {
            throw new NyonException("cannot unmark without a description :(");
        }
        int idx;
        try {
            idx = Integer.parseInt(taskName) - 1;
            if (idx < 0 || idx >= list.size()) {
                throw new NyonException("index out of bounds...");
            }
            Task task = list.get(idx);
            task.uncompleteTask();
            return new Result(String.format("Unmarked %s", task));
        } catch (NumberFormatException e) {
            for (Task task : list) {
                if (task.isSameTask(taskName)) {
                    task.uncompleteTask();
                    return new Result(String.format("Unmarked %s", task));
                }
            }
            return new Result("couldn't find the task... did you spell it right?");
        }

    }
}
