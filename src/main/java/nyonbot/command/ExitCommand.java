package nyonbot.command;

import nyonbot.Logic.Result;

public class ExitCommand extends Command {
    public ExitCommand(String input) {
        super(input);
    }

    @Override
    public Result execute() {
        return new Result("" , true);
    }
}
