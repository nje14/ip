package nyonbot.command;

import java.util.HashMap;

import nyonbot.Logic.Result;
import nyonbot.model.Task;
import nyonbot.model.TaskList;

/**
 * Lists all tasks in the tasklist
 */
public class ListCommand extends Command {
    private TaskList list;
    
    /**
     * Creates a List command with parsed arguments and a TaskList to read.
     * 
     * @param arguments parsed command arguments
     * @param list TaskList to read
     */
    public ListCommand(HashMap<String, String> arguments, TaskList list) {
        super(arguments);
        this.list = list;
    }

    /** {@inheritDoc} */
    @Override
    public Result execute() {
        StringBuilder sb = new StringBuilder("Here are your tasks:\n");
        int idx = 0;
        for (Task task : list) {
            sb.append(String.format("%s. %s", ++idx, task));
            sb.append("\n");
        }
        if (idx == 0) {
            sb.append("No tasks nyon...");
        }
        return new Result(new String(sb));
    }
}
