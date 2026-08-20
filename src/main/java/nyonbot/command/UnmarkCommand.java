package nyonbot.command;

import java.util.ArrayList;

import nyonbot.Logic.Result;
import nyonbot.model.Task;

public class UnmarkCommand extends Command {
    ArrayList<Task> list = new ArrayList<>();

    public UnmarkCommand(String input, ArrayList<Task> list) {
        super(input);
        this.list = list;
    }

    public Result execute() {
        String taskName = this.input.substring("unmark ".length()).strip();
        if (taskName.isBlank()) {
            return new Result("cannot unmark without a description :(");
        }
        for (Task task : list) {
            if (task.isSameTask(taskName)) {
                task.uncompleteTask();
                return new Result(String.format("Unmarked %s as completed", task));
            }
        }
        return new Result("couldn't find the task... did you spell it right?");
    }
}
