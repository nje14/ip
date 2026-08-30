package nyonbot.command;

import java.time.LocalDateTime;
import java.util.HashMap;

import nyonbot.Parser;
import nyonbot.Logic.Result;
import nyonbot.model.Event;
import nyonbot.model.NyonException;
import nyonbot.model.Task;
import nyonbot.model.TaskList;

/**
 * Adds an event with a start time and end time
 */
public class EventCommand extends Command {
    TaskList list;
    public EventCommand(HashMap<String, String> arguments, TaskList list) {
        super(arguments);
        this.list = list;
    }

    /** {@inheritDoc} */
    @Override
    public Result execute() throws NyonException {
        String description = arguments.get(DESCRIPTION_KEY);
        String startValue = arguments.get("--from");
        String endValue = arguments.get("--to");
        if (!arguments.containsKey("--from")) {
            throw new NyonException("use --from to specify the starttime");
        }
        if (!arguments.containsKey("--to")) {
            throw new NyonException("use --to to specify the endtime");
        }
        if (description == null || description.isBlank()) {
            throw new NyonException("cannot omit the description");
        }
        if (startValue.isBlank()) {
            throw new NyonException("cannot omit the start time");
        }
        if (endValue.isBlank()) {
            throw new NyonException("cannot omit the end time");
        }
        LocalDateTime startDate = Parser.parseDate(startValue);
        LocalDateTime endDate = Parser.parseDate(endValue);
        if (startDate == null || endDate == null) {
            throw new NyonException("please enter startDate and endDate in the format dd/MM/yyyy HHmm");
        }
        Task event = new Event(
                description,
                startDate, 
                endDate
        );
        list.add(event);
        return new Result(String.format(
                "I've added this task: \n%s\nThere are %s tasks in your list",
                event, list.size()), false, true);
    }
}
