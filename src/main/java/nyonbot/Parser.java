package nyonbot;

import nyonbot.command.Command;

public class Parser {    
    private static Parser instance = null;
    private Parser() {

    }

    public static synchronized Parser getInstance() {
        if (instance == null) {
            instance = new Parser();
        }
        return instance;
    }
    public Command parse(String input) {
        String[] command = input.split(" ", 1);
        switch (command[0]) {
            case ("bye"):
                return new Command(Command.Commands.EXIT, input);
            case ("nyon"):
                return new Command(Command.Commands.NYON, input);
            case ("echo"):
                return new Command(Command.Commands.ECHO, input);
            case ("list"):
                return new Command(Command.Commands.LIST, input);
            default:
                return new Command(Command.Commands.LIST_INSERT, input);
        }
    }
}
