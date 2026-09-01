package nyonbot;

import java.io.IOException;

import nyonbot.Logic.Result;
import nyonbot.command.Command;
import nyonbot.storage.Storage;

public class NyonBot {
    private Ui ui = Ui.getInstance();
    private Parser parser = Parser.getInstance();
    private Logic logic = Logic.getInstance();
    private Storage storage = new Storage("data/nyonbot.txt");

    public NyonBot() {
        try {
            logic.loadList(storage.load());
        } catch (IOException e) {
            ui.showOutput("couldn't load your list as " + e.getMessage());
        }
    }

    public String respond(String input) {
        try {
            String userInput = input;
            Command cmd = parser.parse(userInput);
            Result res = logic.execute(cmd);
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

    public boolean onClose() {
        try {
            storage.save(logic.getList());
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        Ui ui = Ui.getInstance();
        Parser parser = Parser.getInstance();
        Logic logic = Logic.getInstance();
        Storage storage = new Storage("data/nyonbot.txt");
        try {
            logic.loadList(storage.load());
        } catch (IOException e) {
            ui.showOutput("couldn't load your list as " + e.getMessage());
        }

        ui.welcome();

        boolean loop = true;

        while (loop) {
            System.out.println();
            try {
                String userInput = ui.readCommand();
                Command cmd = parser.parse(userInput);
                Result res = logic.execute(cmd);
                if (res.out() != null && !res.out().isBlank()) {
                    StringBuilder sb = new StringBuilder("Nyon! (");
                    sb.append(res.out());
                    sb.append(")");
                    ui.showOutput(sb.toString());
                }

                loop = !res.shouldExit();
            } catch (Exception e) {
                StringBuilder sb = new StringBuilder("Nyon... (");
                sb.append(e.getMessage());
                sb.append(")");
                ui.showOutput(sb.toString());
            }

        }
        try {
            storage.save(logic.getList());
        } catch (IOException e) {
            ui.showOutput("couldn't save your list as " + e.getMessage());
        }

        ui.goodbye();
    }
}
