package nyonbot.command;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import nyonbot.Parser;
import nyonbot.Logic.Result;
import nyonbot.model.Deadline;
import nyonbot.model.NyonException;
import nyonbot.model.Task;

public class DeadlineCommand extends Command {
    private ArrayList<Task> list;

    public DeadlineCommand(String cmd, ArrayList<Task> list) {
        super(cmd);
        this.list = list;
    }

    @Override
    public Result execute() throws NyonException {
        String deadlineCmd = this.input;
        int deadlineIndex = "deadline ".length();
        int deadLineByIndex = deadlineCmd.indexOf(" /by ");
        if (deadLineByIndex != -1 && deadLineByIndex < deadlineIndex + 1) {
            throw new NyonException("cannot omit the description");
        }
        if (deadLineByIndex == -1) {
            throw new NyonException("use /by to specify the deadline");
        }
        if (deadLineByIndex + " /by ".length() > deadlineCmd.length()) {
            throw new NyonException("cannot omit the deadline date");
        }
        LocalDateTime deadlineTime = Parser.parseDate(deadlineCmd.substring(deadLineByIndex+" /by ".length()));
        if (deadlineTime == null) {
            throw new NyonException("enter the time in the following format: dd/MM/yy HHmm");
        }
        Task deadline = new Deadline(deadlineCmd.substring(deadlineIndex, deadLineByIndex), deadlineTime);
        list.add(deadline);
        return new Result(String.format("I've added this task: \n%s\nThere are %s tasks in your list", deadline, list.size()));
    }
}
