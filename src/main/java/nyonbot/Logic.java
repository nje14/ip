package nyonbot;

import java.util.ArrayList;

import nyonbot.command.Command;


public class Logic {

    public record Result(String out, String message, boolean exit) {
        public Result(String out, String message) {
            this(out, message, false);
        }
    }   

    private static Logic instance = null;
    private ArrayList<String> list;

    private Logic() {
        this.list = new ArrayList<>();
    }

    public static synchronized Logic getInstance() {
        if (instance == null) {
            instance = new Logic();
        }
        return instance;
    }

    public Result execute(Command cmd) {
        switch (cmd.command()) {
            case Command.Commands.EXIT:
                return new Result("", "exit", true);
            case Command.Commands.ECHO:
                String[] out = cmd.message().split(" ", 2);
                return new Result(out.length < 2 ? out[0] : out[1], "echo");
            case Command.Commands.NYON:
                Ui.getInstance().banner();
                return new Result("Nyon", "nyon");
            case Command.Commands.LIST:
                StringBuilder sb = new StringBuilder();
                for (String str: list) {
                    sb.append(str);
                    sb.append("\n");
                }
                return new Result(new String(sb), "ls");
            case Command.Commands.LIST_INSERT:
                list.add(cmd.message());
                return new Result("added "+cmd.message(), "add");
            default:
                return new Result(null, "Unknown command");        
        }
        
    }
}
