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
                    sb.append(String.format("%s. %s", idx++, task));
                    sb.append("\n");
                }
                return new Result(new String(sb), "ls");
            case Command.Commands.TODO:
                String[] todoCmd = cmd.message().split(" ");
                if (todoCmd.length < 2) {
                    return new Result("cannot add a missing description :(", "err");
                }
                Task todo = new ToDo(todoCmd[1]);
                list.add(todo);
                return new Result(String.format("I've added this task:\n%s\nThere are %s tasks in your list", todo, list.size()), "todo");
            case Command.Commands.DEADLINE:
                String[] deadlineCmd = cmd.message().split(" ");
                if (deadlineCmd.length < 2) {
                    return new Result("cannot add an empty description :(", "err");
                }
                if (deadlineCmd.length < 3) {
                    return new Result("cannot omit a date of the deadline :(", "err");
                }
                Task deadline = new Deadline(deadlineCmd[1], deadlineCmd[2]);
                list.add(deadline);
                return new Result(String.format("I've added this task: \n%s\nThere are %s tasks in your list", deadline, list.size()), "deadline");
            case Command.Commands.EVENT:
                String[] eventCmd = cmd.message().split(" ");
                if (eventCmd.length < 2) {
                    return new Result("cannot add an empty description :(", "err");
                } else if (eventCmd.length < 4) {
                    return new Result("cannot omit the start date and end date", "err");
                }
                Task event = new Event(eventCmd[1], eventCmd[2], eventCmd[3]);
                list.add(event);
                return new Result( String.format("I've added this task: \n%s\nThere are %s tasks in your list", event, list.size()), "event");
            default:
                return new Result(null, "Unknown command");        
        }
        
    }
}
