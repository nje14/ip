package nyonbot.command;

import nyonbot.Logic.Result;

/**
 * Echos nyon back to the user because why not
 */
public class NyonCommand extends Command {

    /** {@inheritDoc} */
    @Override
    public Result execute() {
        return new Result("Yay");
    }
}
