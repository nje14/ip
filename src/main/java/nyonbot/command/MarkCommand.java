package nyonbot.command;

import java.util.ArrayList;

import nyonbot.Logic.Result;
import nyonbot.model.Task;

public class MarkCommand extends Command {
    ArrayList<Task> list = new ArrayList<>();

    public MarkCommand(String input, ArrayList<Task> list) {
        super(input);
        this.list = list;
    }

    public Result execute() {
        String[] markCmd = this.input.split(" ", 3);
        if (markCmd.length < 2) {
            return new Result("cannot mark without a description :(");
        }
        for (Task task : list) {

            if (task.isSameTask(markCmd[1])) {
                task.completeTask();
                return new Result(String.format("Marked %s as completed", task));
            }
        }
        return new Result("couldn't find the task... did you spell it right?");
    }
}
