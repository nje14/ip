package nyonbot.command;

import java.io.IOException;

import nyonbot.Logic;
import nyonbot.Logic.Result;
import nyonbot.storage.Storage;

/**
 * Deletes all tasks and clears the storage file
 */
public class WipeCommand extends Command {

    /**
     * Creates a new Wipe command
     */
    public WipeCommand() {
        super();
    }

    /** {@inheritDoc} */
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
