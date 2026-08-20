package nyonbot.command;

import java.util.ArrayList;

import nyonbot.Logic.Result;
import nyonbot.model.Deadline;
import nyonbot.model.Task;

public class DeadlineCommand extends Command {
    private ArrayList<Task> list;

    public DeadlineCommand(String cmd, ArrayList<Task> list) {
        super(cmd);
        this.list = list;
    }

    @Override
    public Result execute() {
        String deadlineCmd = this.input;
        int deadlineIndex = "deadline ".length();
        int deadLineByIndex = deadlineCmd.indexOf(" /by ");
        if (deadLineByIndex <= deadlineIndex + 1) {
            return new Result("cannot omit the description :(");
        }
        if (deadLineByIndex == -1) {
            return new Result("use /by to specify the deadline");
        }
        if (deadLineByIndex + " /by ".length() > deadlineCmd.length()) {
            return new Result("cannot omit the deadline date");
        }
        Task deadline = new Deadline(deadlineCmd.substring(deadlineIndex, deadLineByIndex), deadlineCmd.substring(deadLineByIndex+" /by ".length()));
        list.add(deadline);
        return new Result(String.format("I've added this task: \n%s\nThere are %s tasks in your list", deadline, list.size()));
    }
}
