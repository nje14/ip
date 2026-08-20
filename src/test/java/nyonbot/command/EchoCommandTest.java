package nyonbot.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import nyonbot.Logic.Result;

public class EchoCommandTest {
    @Test
    void echo_test1() {
        EchoCommand command = new EchoCommand("echo hello");
        Result result = command.execute();
        assertEquals("hello", result.out());
        assertFalse(result.exit());
    }
}
