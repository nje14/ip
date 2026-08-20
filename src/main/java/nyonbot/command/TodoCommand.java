package nyonbot.command;

import java.util.ArrayList;

import nyonbot.Logic.Result;
import nyonbot.model.Task;
import nyonbot.model.ToDo;

public class TodoCommand extends Command {
    ArrayList<Task> list = new ArrayList<>();
    public TodoCommand(String input, ArrayList<Task> list) {
        super(input);
        this.list = list;
    }

    public Result execute() {
        String cmd = this.input;
        String[] todoCmd = cmd.split(" ", 2);
        if (todoCmd.length < 2) {
            return new Result("cannot add a missing description :(");
        }
        Task todo = new ToDo(todoCmd[1]);
        list.add(todo);
        return new Result(String.format("I've added this task:\n%s\nThere are %s tasks in your list", todo, list.size()));
    }
}
