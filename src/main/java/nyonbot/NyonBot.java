package nyonbot;

import nyonbot.Logic.Result;
import nyonbot.command.Command;

public class NyonBot {
    public static void main(String[] args) {
        Ui ui = Ui.getInstance();
        Parser parser = Parser.getInstance();
        Logic logic = Logic.getInstance();

        ui.welcome();

        boolean loop = true;

        while (loop) {
            System.out.println();
            String userInput = ui.readCommand();
            Command cmd = parser.parse(userInput);
            Result res = logic.execute(cmd);
            if (res.out() != null && res.out().isBlank()) {
                ui.showOutput(res.out());
            }
            loop = !res.exit();
        }
        ui.goodbye();
    }
}
    