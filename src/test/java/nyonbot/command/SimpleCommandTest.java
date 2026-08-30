package nyonbot.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import nyonbot.Logic.Result;

class SimpleCommandTest {
    @Test
    void echoCommand_message_returnsMessageOnly() {
        Result result = new EchoCommand("echo hello world").execute();

        assertEquals("hello world", result.out());
        assertFalse(result.shouldExit());
    }

    @Test
    void echoCommand_withoutMessage_returnsKeyword() {
        assertEquals("echo", new EchoCommand("echo").execute().out());
    }

    @Test
    void exitCommand_execute_requestsExit() {
        Result result = new ExitCommand("bye").execute();

        assertEquals("", result.out());
        assertTrue(result.shouldExit());
    }

    @Test
    void noCommand_execute_returnsFallbackMessage() {
        assertEquals("nyon...?", new NoCommand().execute().out());
    }

    @Test
    void nyonCommand_execute_returnsBannerAndNyonMessage() {
        String output = new NyonCommand().execute().out();

        assertTrue(output.contains("Nyon"));
        assertTrue(output.endsWith("\nNyon!"));
    }
}
