package nyonbot;

import nyonbot.command.Command;
import nyonbot.command.CommandType;
import nyonbot.command.DeadlineCommand;
import nyonbot.command.EchoCommand;
import nyonbot.command.EventCommand;
import nyonbot.command.ExitCommand;
import nyonbot.command.ListCommand;
import nyonbot.command.MarkCommand;
import nyonbot.command.NoCommand;
import nyonbot.command.NyonCommand;
import nyonbot.command.TodoCommand;
import nyonbot.command.UnmarkCommand;

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
        if (command.length == 0)  {
            return new NoCommand();
        }
        CommandType type = CommandType.toCommandType(command[0]);
        System.out.println(type);
        return switch (type) {
            case null -> new NoCommand();
            case EXIT -> new ExitCommand(input);
            case ECHO -> new EchoCommand(input);
            case NYON -> new NyonCommand();
            case LIST -> new ListCommand(input, Logic.getInstance().getList());
            case TODO -> new TodoCommand(input, Logic.getInstance().getList());
            case DEADLINE -> new DeadlineCommand(input, Logic.getInstance().getList());
            case EVENT -> new EventCommand(input, Logic.getInstance().getList());
            case MARK -> new MarkCommand(input, Logic.getInstance().getList());
            case UNMARK -> new UnmarkCommand(input, Logic.getInstance().getList());

            default -> throw new IllegalArgumentException("unrecognized command");
        };
    }
}
