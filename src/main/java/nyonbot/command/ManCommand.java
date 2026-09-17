package nyonbot.command;

import nyonbot.Logic.Result;

/**
 * Easter egg command that creates a Man event
 */
public class ManCommand extends Command {
    public static final String MANTEXT = "Well, there is a man here";

    /**
     * Creates a Man Command
     */
    public ManCommand() {

    }

    /** {@inheritDoc} */
    @Override
    public Result execute() {
        return new Result(MANTEXT, false, false, 66);
    }
}
