package nyonbot.command;

import nyonbot.ResourceLoader;
import nyonbot.Logic.Result;

public class NyonCommand extends Command {
    @Override
    public Result execute() {
        StringBuilder sb = new StringBuilder();
        sb.append(ResourceLoader.readTextFile("static/ascii-banner.txt"));
        sb.append("\nNyon!");
        return new Result(sb.toString());
    }
}
