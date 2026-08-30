package nyonbot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nyonbot.command.DeadlineCommand;
import nyonbot.command.EchoCommand;
import nyonbot.command.ExitCommand;
import nyonbot.command.ListCommand;
import nyonbot.command.TodoCommand;

class ParserTest {
    private final Parser parser = Parser.getInstance();

    @BeforeEach
    void clearTaskList() {
        Logic.getInstance().getList().clear();
    }

    @Test
    void parse_knownCommands_returnsCorrespondingCommandType() {
        assertInstanceOf(ExitCommand.class, parser.parse("bye"));
        assertInstanceOf(EchoCommand.class, parser.parse("echo hello"));
        assertInstanceOf(ListCommand.class, parser.parse("list"));
        assertInstanceOf(TodoCommand.class, parser.parse("todo read book"));
        assertInstanceOf(DeadlineCommand.class,
                parser.parse("deadline submit report /by 31/12/2026 2359"));
    }

    @Test
    void parse_leadingAndTrailingWhitespace_ignoresWhitespace() {
        assertInstanceOf(EchoCommand.class, parser.parse("   echo hello   "));
    }

    @Test
    void parseDate_validDate_returnsLocalDateTime() {
        assertEquals(LocalDateTime.of(2026, 12, 31, 23, 59),
                Parser.parseDate("31/12/2026 2359"));
    }

    @Test
    void parseDate_invalidDate_returnsNull() {
        assertNull(Parser.parseDate("32/13/2026 1200"));
        assertNull(Parser.parseDate("2026-12-31 1200"));
    }
}
