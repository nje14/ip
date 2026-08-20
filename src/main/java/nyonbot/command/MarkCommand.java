package nyonbot.command;

import java.util.ArrayList;

import nyonbot.Logic.Result;
import nyonbot.model.NyonException;
import nyonbot.model.Task;

public class MarkCommand extends Command {
    ArrayList<Task> list = new ArrayList<>();

    public MarkCommand(String input, ArrayList<Task> list) {
        super(input);
        this.list = list;
    }

    public Result execute() throws NyonException {
        String taskName = this.input.substring("mark ".length()).strip();
        if (taskName.isBlank()) {
            throw new NyonException("cannot mark without a description");
        }
        for (Task task : list) {

            if (task.isSameTask(taskName)) {
                task.completeTask();
                return new Result(String.format("Marked %s as completed", task));
            }
        }
        return new Result("couldn't find the task... did you spell it right?");
    }
}
