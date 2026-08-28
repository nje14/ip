package nyonbot.command;


import nyonbot.Logic.Result;
import nyonbot.model.NyonException;
import nyonbot.model.Task;
import nyonbot.model.TaskList;
import nyonbot.model.ToDo;

public class TodoCommand extends Command {
    TaskList list;
    public TodoCommand(String input, TaskList list) {
        super(input);
        this.list = list;
    }

    public Result execute() throws NyonException {
        String cmd = this.input;
        String[] todoCmd = cmd.split(" ", 2);
        if (todoCmd.length < 2) {
            throw new NyonException("cannot add a missing description");
        }
        Task todo = new ToDo(todoCmd[1]);
        list.add(todo);
        return new Result(String.format("I've added this task:\n%s\nThere are %s tasks in your list", todo, list.size()));
    }
}
