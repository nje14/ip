package nyonbot;

import java.util.ArrayList;

import nyonbot.command.Command;

import nyonbot.model.Task;



public class Logic {

    public record Result(String out, boolean exit) {
        public Result(String out) {
            this(out, false);
        }
    }   



    private static Logic instance = null;
    private ArrayList<Task> list;

    private Logic() {
        this.list = new ArrayList<>();
    }

    public static synchronized Logic getInstance() {
        if (instance == null) {
            instance = new Logic();
        }
        return instance;
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public Result execute(Command cmd) throws Exception{
        return cmd.execute();     
    }
}
