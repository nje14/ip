package nyonbot.command;

import java.util.HashMap;
import java.util.HashSet;
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
     * @param arguments parsed command arguments
     * @param task the TaskList to search through
     */
    public FindCommand(HashMap<String, String> arguments, TaskList tasks) {
        super(arguments);
        this.list = tasks;
    }

    /** {@inheritDoc} */
    @Override
    public Result execute() throws NyonException {
        assert arguments != null;
        String searchInput = arguments.get(DESCRIPTION_KEY);
        if (searchInput == null || searchInput.isBlank()) {
            throw new NyonException("specify a search string");
        }
        String[] searchStrings = searchInput.split(" ");
        Set<Task> matchingTasks = new HashSet<>();
        for (Task task : list) {
            String[] fragments = task.getName().split(" ");
            for (String searchTerm: searchStrings) {
                for (String fragment: fragments) {
                    if (fragment.indexOf(searchTerm) != -1) {
                        matchingTasks.add(task);
                    }
                }
            }
        }
        if (matchingTasks.size() == 0) {
            return new Result("couldn't find anything matching the search string...");
        }
        TaskList taskList = new TaskList();
        taskList.addAll(matchingTasks);
        return new ListCommand(arguments, taskList).execute();
    }
}
