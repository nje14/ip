package nyonbot.command;

import java.util.HashMap;

import nyonbot.Logic.Result;

/**
 * Creates a command to exit the program
 */
public class ExitCommand extends Command {

    /**
     * Creates an exit command.
     * <p>
     * Parsed arguments are required for consistency but are unused.
     * 
     * @param arguments parsed command arguments
     */
    public ExitCommand(HashMap<String, String> arguments) {
        super(arguments);
    }

    /** {@inheritDoc} */
    @Override
    public Result execute() {
        return new Result("" , true);
    }
}
