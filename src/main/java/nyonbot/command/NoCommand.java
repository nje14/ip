package nyonbot.command;

import nyonbot.Logic.Result;

/**
 * Creates a NoCommand
 * @deprecated
 */
public class NoCommand extends Command {
    /** {@inheritDoc} */
    @Override
    public Result execute() {
        return new Result("nyon...?");
    }
}
