package nyonbot.command;

import nyonbot.Logic.Result;

/**
 * Creates a command to exit the program
 */
public class ExitCommand extends Command {

    /**
     * Creates an exit command.
     * <p>
     * Input is required for consistency but is unused.
     * 
     * @param input raw input associated with this command
     */
    public ExitCommand(String input) {
        super(input);
    }

    /** {@inheritDoc} */
    @Override
    public Result execute() {
        return new Result("" , true);
    }
}
