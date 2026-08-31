package nyonbot.command;

import java.util.Arrays;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;

import nyonbot.Logic.Result;
import nyonbot.model.NyonException;
import nyonbot.model.Task;
import nyonbot.model.TaskList;

/**
 * Finds and returns all tasks matching an input keyword(s)
 */
public class FindCommand extends Command {
    private TaskList list;

    /**
     * Creates a FindCommand with parsed arguments and a TaskList to search.
     * 
     * @param arguments parsed command arguments
     * @param task the TaskList to search through
     */
    public FindCommand(HashMap<String, String> arguments, TaskList task) {
        super(arguments);
        this.list = task;
    }

    /** {@inheritDoc} */
    @Override
    public Result execute() throws NyonException{
        String searchInput = arguments.get(DESCRIPTION_KEY);
        if (searchInput == null || searchInput.isBlank()) {
            throw new NyonException("specify a search string");
        }
        String[] searchStrings = searchInput.split(" ");
        Set<String> set = new HashSet<>(Arrays.asList(searchStrings));
        TaskList tasklist = new TaskList();
        for (Task task : list) {
            for (String fragment: task.getName().split(" ")) {
                if (set.contains(fragment)) {
                    tasklist.add(task);
                }
            }
        }
        if (tasklist.size() == 0) {
            return new Result("couldn't find anything matching the search string...");
        }
        return new ListCommand(arguments, tasklist).execute();
    }
}   
