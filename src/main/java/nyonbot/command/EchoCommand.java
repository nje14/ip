package nyonbot.command;

import nyonbot.Logic.Result;

/**
 * Echos the user input
 */
public class EchoCommand extends Command {
    public EchoCommand(String input) {
        super(input);
    }

    /** {@inheritDoc} */
    @Override
    public Result execute() {
        String[] out = this.input.split(" ", 2);
        return new Result(out.length < 2 ? out[0] : out[1]);
    }
}
