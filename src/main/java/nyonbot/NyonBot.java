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
        ui.goodbye();
    }
}
