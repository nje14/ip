package nyonbot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private static final Pattern FLAG_PATTERN =
            Pattern.compile("(?<!\\S)(--[A-Za-z][A-Za-z0-9-]*)(?=\\s|$)");

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
        HashMap<String, String> arguments = parseArguments(input);
        String command = arguments.get(Command.COMMAND_KEY);
        if (command == null || command.isBlank()) {
            return new NoCommand();
        }
        CommandType type = CommandType.toCommandType(command);
        return switch (type) {
            case EXIT -> new ExitCommand(arguments);
            case ECHO -> new EchoCommand(arguments);
            case NYON -> new NyonCommand();
            case LIST -> new ListCommand(arguments, Logic.getInstance().getList());
            case TODO -> new TodoCommand(arguments, Logic.getInstance().getList());
            case DEADLINE -> new DeadlineCommand(arguments, Logic.getInstance().getList());
            case EVENT -> new EventCommand(arguments, Logic.getInstance().getList());
            case MARK -> new MarkCommand(arguments, Logic.getInstance().getList());
            case UNMARK -> new UnmarkCommand(arguments, Logic.getInstance().getList());
            case DELETE -> new DeleteCommand(arguments, Logic.getInstance().getList());
            case WIPE -> new WipeCommand();
            case FIND -> new FindCommand(arguments, Logic.getInstance().getList());
            default -> throw new IllegalArgumentException("unrecognized command");
        };
    }

    /**
     * Parses command-line input into a command, positional description, and
     * flag-value pairs.
     *
     * @param input raw command-line input
     * @return parsed arguments keyed by {@code command}, {@code description},
     *         or their literal flag such as {@code --by}
     */
    public HashMap<String, String> parseArguments(String input) {
        HashMap<String, String> arguments = new HashMap<>();
        String normalizedInput = input == null ? "" : input.strip();
        if (normalizedInput.isBlank()) {
            return arguments;
        }

        String[] commandParts = normalizedInput.split("\\s+", 2);
        arguments.put(Command.COMMAND_KEY, commandParts[0]);
        if (commandParts.length == 1) {
            return arguments;
        }

        String rawArguments = commandParts[1];
        Matcher matcher = FLAG_PATTERN.matcher(rawArguments);
        int firstFlagIndex = rawArguments.length();
        int previousFlagValueIndex = -1;
        String previousFlag = null;

        while (matcher.find()) {
            if (previousFlag == null) {
                firstFlagIndex = matcher.start();
            } else {
                arguments.put(previousFlag,
                        rawArguments.substring(previousFlagValueIndex, matcher.start()).strip());
            }
            previousFlag = matcher.group(1);
            previousFlagValueIndex = matcher.end();
        }

        String description = rawArguments.substring(0, firstFlagIndex).strip();
        if (!description.isBlank()) {
            arguments.put(Command.DESCRIPTION_KEY, description);
        }
        if (previousFlag != null) {
            arguments.put(previousFlag,
                    rawArguments.substring(previousFlagValueIndex).strip());
        }
        return arguments;
    }

    /**
     * Parses a String date using the format {@code dd/MM/yyyy HHmm}
     * 
     * @param date date to be parsed
     * @return the <code>LocalDateTime</code> associated with this date
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
