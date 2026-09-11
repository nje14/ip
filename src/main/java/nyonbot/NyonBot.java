package nyonbot;

import java.io.IOException;

import javafx.application.Platform;
import nyonbot.Logic.Result;
import nyonbot.command.Command;
import nyonbot.model.NyonException;
import nyonbot.storage.ListStorage;

/**
 * Main driver class for NyonBot
 */
public class NyonBot {
    private Ui ui = Ui.getInstance();
    private Parser parser = Parser.getInstance();
    private Logic logic = Logic.getInstance();
    private ListStorage storage = ListStorage.getInstance();

    /**
     * Creates a NyonBot instance
     */
    public NyonBot() {
        try {
            logic.loadList(storage.load());
        } catch (IOException e) {
            ui.showOutput("couldn't load your list as " + e.getMessage());
        }
    }

    /**
     * Passes in an input to NyonBot
     * @param input
     * @return String response
     */
    public String respond(String input) {
        try {
            String userInput = input;
            Command cmd = parser.parse(userInput);
            Result res = logic.execute(cmd);
            if (res.shouldExit()) {
                if (!this.onClose()) {
                    throw new NyonException("couldn't close file");
                }
                Platform.exit();
                return null;
            }
            if (res.shouldWrite()) {
                storage.save(logic.getList());
            }
            if (res.out() != null && !res.out().isBlank()) {

                StringBuilder sb = new StringBuilder("Nyon! (");
                sb.append(res.out());
                sb.append(")");
                return sb.toString();
            }
            return "";
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder("Nyon... (");
            sb.append(e.getMessage());
            sb.append(")");
            return sb.toString();
        }
    }

    /**
     * Invoked when NyonBot is closes; saves the current list
     * @return true if successfully written to file, false otherwise
     */
    public boolean onClose() {
        try {
            storage.save(logic.getList());
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        NyonBot nyonBot = new NyonBot();
        Ui ui = Ui.getInstance();

        ui.welcome();

        while (true) {
            System.out.println();

            String response = nyonBot.respond(ui.readCommand());

            if (response == null) {
                break;
            }
            if (!response.isBlank()) {
                ui.showOutput(response);
            }
        }

        ui.goodbye();
    }
}
