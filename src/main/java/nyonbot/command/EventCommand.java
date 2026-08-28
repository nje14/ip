package nyonbot.command;

import java.time.LocalDateTime;
import java.util.ArrayList;

import nyonbot.Parser;
import nyonbot.Logic.Result;
import nyonbot.model.Event;
import nyonbot.model.NyonException;
import nyonbot.model.Task;

public class EventCommand extends Command {
    ArrayList<Task> list = new ArrayList<>();
    public EventCommand(String input, ArrayList<Task> list) {
        super(input);
        this.list = list;
    }

    public Result execute() throws NyonException {
        String eventCmd = this.input;
        int eventIndex = "event ".length();
        int eventFromIndex = eventCmd.indexOf(" /from ");
        int eventToIndex = eventCmd.indexOf(" /to ");
        if (eventFromIndex == -1) {
            throw new NyonException("use /from to specify the starttime");
        }
        if (eventToIndex == -1) {
            throw new NyonException("use /to to specify the endtime");
        }
        if (eventIndex + 1 >= Math.min(eventFromIndex, eventToIndex)) {
            throw new NyonException("cannot omit the description");
        }
        if (eventFromIndex + " /from ".length() > eventCmd.length()) {
            throw new NyonException("cannot omit the start time");
        }
        if (eventToIndex + " /to ".length() > eventCmd.length()) {
            throw new NyonException("cannot omit the end time");
        }
        if (eventFromIndex > eventToIndex) {
            throw new NyonException("usage: event <eventname> /from <starttime> /to <endtime>");
        }
        LocalDateTime startDate = Parser.parseDate(eventCmd.substring(eventFromIndex + " /from ".length(), eventToIndex));
        LocalDateTime endDate = Parser.parseDate(eventCmd.substring(eventToIndex + " /to ".length()));
        if (startDate == null || endDate == null) {
            throw new NyonException("please enter startDate and endDate in the format dd/MM/yyyy HHmm");
        }
        Task event = new Event(
                eventCmd.substring(eventIndex, eventFromIndex), 
                startDate, 
                endDate
        );
        list.add(event);
        return new Result(String.format("I've added this task: \n%s\nThere are %s tasks in your list", event, list.size()), false, true);
    }
}
