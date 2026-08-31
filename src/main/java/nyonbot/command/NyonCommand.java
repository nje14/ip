package nyonbot.command;

import nyonbot.ResourceLoader;
import nyonbot.Logic.Result;

/**
 * Echos nyon back to the user because why not
 */
public class NyonCommand extends Command {

    /** {@inheritDoc} */
    @Override
    public Result execute() {
        StringBuilder sb = new StringBuilder();
        sb.append(ResourceLoader.readTextFile("static/ascii-banner.txt"));
        sb.append("\nNyon!");
        return new Result(sb.toString());
    }
}
