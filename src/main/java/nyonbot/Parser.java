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
        input.strip();
        input.trim();
        String[] command = input.split(" ", 2);
        switch (command[0]) {
            case ("bye"):
                return new Command(Command.Commands.EXIT, input);
            case ("nyon"):
                return new Command(Command.Commands.NYON, input);
            case ("echo"):
                return new Command(Command.Commands.ECHO, input);
            case ("list"):
                return new Command(Command.Commands.LIST, input);
            case ("todo"):
                return new Command(Command.Commands.TODO, input);
            case ("deadline"):
                return new Command(Command.Commands.DEADLINE, input);
            case ("event"):
                return new Command(Command.Commands.EVENT, input);
            case ("mark"):
                return new Command(Command.Commands.MARK, input);
            case ("unmark"):
                return new Command(Command.Commands.UNMARK, input);
            default:
                return new Command(Command.Commands.ECHO, input);
        }
    }
}
