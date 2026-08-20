package nyonbot.command;

import nyonbot.Logic.Result;

public class NoCommand extends Command {
    @Override
    public Result execute() {
        return new Result("nyon...?");
    }
}
