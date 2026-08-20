package nyonbot.command;

import java.util.ArrayList;

import nyonbot.Logic.Result;
import nyonbot.model.Event;
import nyonbot.model.Task;

public class EventCommand extends Command {
    ArrayList<Task> list = new ArrayList<>();
    public EventCommand(String input, ArrayList<Task> list) {
        super(input);
        this.list = list;
    }

    public Result execute() {
        String eventCmd = this.input;
        int eventIndex = "event ".length();
        int eventFromIndex = eventCmd.indexOf(" /from ");
        int eventToIndex = eventCmd.indexOf(" /to ");
        if (eventFromIndex == -1) {
            return new Result("use /from to specify the start time");
        }
        if (eventToIndex == -1) {
            return new Result("use /to to specify the endtime");
        }
        if (eventIndex + 1 >= Math.min(eventFromIndex, eventToIndex)) {
            return new Result("cannot omit the description");
        }
        if (eventFromIndex + " /from ".length() > eventCmd.length()) {
            return new Result("cannot omit the start time");
        }
        if (eventToIndex + " /to ".length() > eventCmd.length()) {
            return new Result("cannot omit the end time");
        }
        if (eventFromIndex > eventToIndex) {
            return new Result("usage: event <eventname> /from <starttime> /to <endtime>");
        }
        Task event = new Event(eventCmd.substring(eventIndex, eventFromIndex), eventCmd.substring(eventFromIndex + " /from ".length(), eventToIndex), eventCmd.substring(eventToIndex + " /to ".length()));
        list.add(event);
        return new Result(String.format("I've added this task: \n%s\nThere are %s tasks in your list", event, list.size()));
    }
}
