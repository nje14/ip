package nyonbot.command;

import java.util.ArrayList;

import nyonbot.Logic.Result;
import nyonbot.model.Task;

public class ListCommand extends Command {
    private ArrayList<Task> list;
    
    public ListCommand(String input, ArrayList<Task> list) {
        super(input);
        this.list = list;
    }

    @Override
    public Result execute() {
        StringBuilder sb = new StringBuilder("Here are your tasks:\n");
        int idx = 0;
        for (Task task: list) {
            sb.append(String.format("%s. %s", ++idx, task));
            sb.append("\n");
        }
        return new Result(new String(sb));
    }
}
