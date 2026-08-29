package nyonbot.command;

import nyonbot.Logic.Result;

public class EchoCommand extends Command {
    public EchoCommand(String input) {
        super(input);
    }

    @Override
    public Result execute() {
        String[] out = this.input.split(" ", 2);
        return new Result(out.length < 2 ? out[0] : out[1]);
    }
}
