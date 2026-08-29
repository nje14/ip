package nyonbot.command;

import java.util.Arrays;
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
     * Creates a new FindCommand with the given raw input and TaskList to search
     * 
     * @param input the raw command input
     * @param task the TaskList to search through
     */
    public FindCommand(String input, TaskList task) {
        super(input);
        this.list = task;
    }

    /** {@inheritDoc} */
    @Override
    public Result execute() throws NyonException{
        String[] eventCmd = input.split(" ", 2);
        if (eventCmd.length <= 1 || eventCmd[1].length() == 0) {
            throw new NyonException("specify a search string");
        }
        String[] searchStrings = eventCmd[1].split(" ");
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
        return new ListCommand(input, tasklist).execute();
    }
}   
