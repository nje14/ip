package nyonbot.command;



import nyonbot.Logic.Result;
import nyonbot.model.Task;
import nyonbot.model.TaskList;

public class ListCommand extends Command {
    private TaskList list;
    
    public ListCommand(String input, TaskList list) {
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
        if (idx == 0) {
            sb.append("No tasks nyon...");
        }
        return new Result(new String(sb));
    }
}
