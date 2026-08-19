package nyonbot;

import java.util.ArrayList;

import nyonbot.command.Command;
import nyonbot.model.Deadline;
import nyonbot.model.Event;
import nyonbot.model.Task;
import nyonbot.model.ToDo;


public class Logic {

    public record Result(String out, String message, boolean exit) {
        public Result(String out, String message) {
            this(out, message, false);
        }
    }   



    private static Logic instance = null;
    private ArrayList<Task> list;

    private Logic() {
        this.list = new ArrayList<>();
    }

    public static synchronized Logic getInstance() {
        if (instance == null) {
            instance = new Logic();
        }
        return instance;
    }

    public Result execute(Command cmd) {
        switch (cmd.command()) {
            case Command.Commands.EXIT:
                return new Result("", "exit", true);
            case Command.Commands.ECHO:
                String[] out = cmd.message().split(" ", 2);
                return new Result(out.length < 2 ? out[0] : out[1], "echo");
            case Command.Commands.NYON:
                Ui.getInstance().banner();
                return new Result("Nyon", "nyon");
            case Command.Commands.LIST:
                StringBuilder sb = new StringBuilder("Here are your tasks:\n");
                int idx = 0;
                for (Task task: list) {
                    sb.append(String.format("%s. %s", ++idx, task));
                    sb.append("\n");
                }
                return new Result(new String(sb), "ls");
            case Command.Commands.TODO:
                String[] todoCmd = cmd.message().split(" ", 2);
                if (todoCmd.length < 2) {
                    return new Result("cannot add a missing description :(", "err");
                }
                Task todo = new ToDo(todoCmd[1]);
                list.add(todo);
                return new Result(String.format("I've added this task:\n%s\nThere are %s tasks in your list", todo, list.size()), "todo");
            case Command.Commands.DEADLINE:
                String deadlineCmd = cmd.message();
                int deadlineIndex = "deadline ".length();
                int deadLineByIndex = deadlineCmd.indexOf(" /by ");
                if (deadLineByIndex == -1) {
                    return new Result("use /by to specify the deadline", "err");
                }
                if (deadLineByIndex <= deadlineIndex + 1) {
                    return new Result("cannot omit the description :(", "err");
                }
                if (deadLineByIndex + " /by ".length() > deadlineCmd.length()) {
                    return new Result("cannot omit the deadline date", "err");
                }
                Task deadline = new Deadline(deadlineCmd.substring(deadlineIndex, deadLineByIndex), deadlineCmd.substring(deadLineByIndex+" /by ".length()));
                list.add(deadline);
                return new Result(String.format("I've added this task: \n%s\nThere are %s tasks in your list", deadline, list.size()), "deadline");
            case Command.Commands.EVENT:
                String eventCmd = cmd.message();
                int eventIndex = "event ".length();
                int eventFromIndex = eventCmd.indexOf(" /from ");
                int eventToIndex = eventCmd.indexOf(" /to ");
                if (eventFromIndex == -1) {
                    return new Result("use /from to specify the start time", "err");
                }
                if (eventToIndex == -1) {
                    return new Result("use /to to specify the endtime", "err");
                }
                if (eventIndex + 1 >= Math.min(eventFromIndex, eventToIndex)) {
                    return new Result("cannot omit the description", "err");
                }
                if (eventFromIndex + " /from ".length() > eventCmd.length()) {
                    return new Result("cannot omit the start time", "err");
                }
                if (eventFromIndex + " /to ".length() > eventCmd.length()) {
                    return new Result("cannot omit the end time", "err");
                }
                Task event = new Event(eventCmd.substring(eventIndex, eventFromIndex), eventCmd.substring(eventFromIndex + " /from ".length(), eventToIndex), eventCmd.substring(eventToIndex + " /to ".length()));
                list.add(event);
                return new Result(String.format("I've added this task: \n%s\nThere are %s tasks in your list", event, list.size()), "deadline");
                
            case Command.Commands.MARK:
                String[] markCmd = cmd.message().split(" ", 3);
                if (markCmd.length < 2) {
                    return new Result("cannot mark without a description :(", "err");
                }
                for (Task task: list) {

                    if (task.isSameTask(markCmd[1])) {
                        task.completeTask();
                        return new Result(String.format("Marked %s as completed", task), "mark");
                    }
                }
                return new Result("couldn't find the task... did you spell it right?", "err");
            case Command.Commands.UNMARK:
                String[] unmarkCmd = cmd.message().split(" ", 3);
                if (unmarkCmd.length < 2) {
                    return new Result("cannot unmark without a description :(", "err");
                }
                for (Task task: list) {
                    if (task.isSameTask(unmarkCmd[1])) {
                        task.uncompleteTask();
                        return new Result(String.format("Unmarked %s as completed", task), "unmark");
                    }
                }
                return new Result("couldn't find the task... did you spell it right?", "err");
            default:
                return new Result(null, "Unknown command");        
        }
        
    }
}
