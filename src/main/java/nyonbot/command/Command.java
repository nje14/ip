package nyonbot.command;

import nyonbot.Logic.Result;

public abstract class Command {
    protected String input;

    public Command() {
        this.input = "";
    }

    public Command(String input) {
        this.input = input;
    }

    public abstract Result execute();

}
