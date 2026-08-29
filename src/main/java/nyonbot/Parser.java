package nyonbot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import nyonbot.command.Command;
import nyonbot.command.CommandType;
import nyonbot.command.DeadlineCommand;
import nyonbot.command.DeleteCommand;
import nyonbot.command.EchoCommand;
import nyonbot.command.EventCommand;
import nyonbot.command.ExitCommand;
import nyonbot.command.FindCommand;
import nyonbot.command.ListCommand;
import nyonbot.command.MarkCommand;
import nyonbot.command.NoCommand;
import nyonbot.command.NyonCommand;
import nyonbot.command.TodoCommand;
import nyonbot.command.UnmarkCommand;
import nyonbot.command.WipeCommand;

/**
 * Converts the command line input into executable commands
 * 
 * @author nje14
 */
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

    /**
     * Parses a raw input into a command
     * 
     * @param input raw input
     * @return the <code>Command</code> associated with this input
     */
    public Command parse(String input) {
        input = input.strip().trim();
        String[] command = input.split("\\s+", 2);
        if (command.length == 0)  {
            return new NoCommand();
        }
        CommandType type = CommandType.toCommandType(command[0]);
        return switch (type) {
            case EXIT -> new ExitCommand(input);
            case ECHO -> new EchoCommand(input);
            case NYON -> new NyonCommand();
            case LIST -> new ListCommand(input, Logic.getInstance().getList());
            case TODO -> new TodoCommand(input, Logic.getInstance().getList());
            case DEADLINE -> new DeadlineCommand(input, Logic.getInstance().getList());
            case EVENT -> new EventCommand(input, Logic.getInstance().getList());
            case MARK -> new MarkCommand(input, Logic.getInstance().getList());
            case UNMARK -> new UnmarkCommand(input, Logic.getInstance().getList());
            case DELETE -> new DeleteCommand(input, Logic.getInstance().getList());
            case WIPE -> new WipeCommand();
            case FIND -> new FindCommand(input, Logic.getInstance().getList());
            default -> throw new IllegalArgumentException("unrecognized command");
        };
    }

    /**
     * Parses a String date using the format {@code dd/MM/yyyy HHmm}
     * 
     * @param date date to be parsed
     * @return the <code>LocalDateTime</cpde> associated with this date
     */
    public static LocalDateTime parseDate(String date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        try {
            return LocalDateTime.parse(date, dtf);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
