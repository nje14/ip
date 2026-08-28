package nyonbot.command;

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
        Storage storage = new Storage("src/main/java/nyonbot/data/nyonbot.txt");
        storage.wipe();
        return new Result("everything is gone now", false);
    }
}
