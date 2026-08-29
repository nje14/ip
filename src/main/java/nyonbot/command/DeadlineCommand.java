package nyonbot.command;


import java.time.LocalDateTime;


import nyonbot.Parser;
import nyonbot.Logic.Result;
import nyonbot.model.Deadline;
import nyonbot.model.NyonException;
import nyonbot.model.Task;
import nyonbot.model.TaskList;

public class DeadlineCommand extends Command {
    private TaskList list;

    public DeadlineCommand(String cmd, TaskList list) {
        super(cmd);
        this.list = list;
    }

    @Override
    public Result execute() throws NyonException {
        String deadlineCmd = this.input;
        int deadlineIndex = "deadline ".length();
        int deadlineByIndex = deadlineCmd.indexOf(" /by ");
        if (deadlineByIndex != -1 && deadlineByIndex < deadlineIndex + 1) {
            throw new NyonException("deadlineByIndex omit the description");
        }
        if (deadlineByIndex == -1) {
            throw new NyonException("use /by to specify the deadline");
        }
        if (deadlineByIndex + " /by ".length() > deadlineCmd.length()) {
            throw new NyonException("cannot omit the deadline date");
        }
        LocalDateTime deadlineTime = Parser.parseDate(deadlineCmd.substring(deadlineByIndex + " /by ".length()));
        if (deadlineTime == null) {
            throw new NyonException("enter the time in the following format: dd/MM/yy HHmm");
        }
        Task deadline = new Deadline(deadlineCmd.substring(deadlineIndex, deadlineByIndex), deadlineTime);
        list.add(deadline);
        return new Result(String.format("I've added this task: \n%s\nThere are %s tasks in your list", deadline, list.size()));
    }
}
