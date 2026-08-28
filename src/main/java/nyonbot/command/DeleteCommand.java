package nyonbot.command;

import java.util.ArrayList;

import nyonbot.Logic.Result;
import nyonbot.model.NyonException;
import nyonbot.model.Task;
import nyonbot.model.TaskList;

public class DeleteCommand extends Command {
    TaskList list;

    public DeleteCommand(String input, TaskList list) {
        super(input);
        this.list = list;
    }

    @Override
    public Result execute() throws NyonException {
        String taskName = this.input.substring("delete ".length()).strip();
        try {
            Integer.parseInt(taskName);
        } catch (NumberFormatException e) {
            Task toRemove = null;
            for (Task task: list) {
                if (task.isSameTask(taskName)) {
                    toRemove = task;
                    break;
                }
            }
            if (toRemove == null) {
                throw new NyonException("couldn't find the task. did you spell it right?");
            }
            list.remove(toRemove);
            return new Result(String.format("I've removed %s from your list", toRemove));
        }
        int taskNumber = Integer.parseInt(taskName) - 1;
        if (taskNumber < 0 || taskNumber >= list.size()) {
            throw new NyonException("index out of bounds");
        }
        Task removed = list.remove(taskNumber);
        return new Result(String.format("I've removed %s from your list", removed));
    }
}
