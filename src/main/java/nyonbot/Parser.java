package nyonbot;

import nyonbot.command.Command;
import nyonbot.command.CommandType;
import nyonbot.command.ExitCommand;

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
        input = input.strip().trim();
        String[] command = input.split("\\s+", 2);
        CommandType type = CommandType.toCommandType(command[0]);
        return switch (type) {
            case EXIT -> new ExitCommand(input);
            default -> throw new IllegalArgumentException("unrecognized command");
        };
    }
}
