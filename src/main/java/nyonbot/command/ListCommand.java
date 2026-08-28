package nyonbot.command;

import nyonbot.Logic.Result;
import nyonbot.model.Task;
import nyonbot.model.TaskList;

/**
 * Lists all tasks in the tasklist
 */
public class ListCommand extends Command {
    private TaskList list;
    
    /**
     * Creates a new List command with the raw input and TaskList to be read
     * 
     * @param input raw command input
     * @param list TaskList to read
     */
    public ListCommand(String input, TaskList list) {
        super(input);
        this.list = list;
    }

    /** {@inheritDoc} */
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
