package nyonbot.command;

import nyonbot.Logic.Result;
import nyonbot.model.NyonException;

/**
 * Represents an executable user command
 */
public abstract class Command {
    protected String input;

    public Command() {
        this.input = "";
    }

    public Command(String input) {
        this.input = input;
    }

    /**
     * Executes this command
     * 
     * @return <code>Result</code> containing output and application control info
     * @throws NyonException if command input is invalid or otherwise raises issues
     */
    public abstract Result execute() throws NyonException;

}
