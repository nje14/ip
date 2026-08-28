package nyonbot.command;

import java.io.IOException;

import nyonbot.Logic;
import nyonbot.Logic.Result;
import nyonbot.storage.Storage;

public class WipeCommand extends Command {
    public WipeCommand() {
        super();
    }

    @Override
    public Result execute() {
        Logic.getInstance().getList().clear();
        Storage storage = new Storage("data/nyonbot.txt");
        try {
            storage.wipe();
        } catch (IOException e) {
            return new Result("couldn't wipe your storage;" + e.getMessage());
        }
        
        return new Result("everything is gone now", false);
    }
}
