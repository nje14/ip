package nyonbot;

import nyonbot.command.Command;

import nyonbot.model.Task;
import nyonbot.model.TaskList;

public class Logic {

    public record Result(String out, boolean shouldExit, boolean shouldWrite) {
        public Result(String out) {
            this(out, false, false);
        }
        public Result(String out, boolean shouldExit) {
            this(out, shouldExit, false);
        }
    }   



    private static Logic instance = null;
    private TaskList list;

    private Logic() {
        this.list = new TaskList();
    }

    public static synchronized Logic getInstance() {
        if (instance == null) {
            instance = new Logic();
        }
        return instance;
    }

    public TaskList getList() {
        return this.list;
    }

    public void loadList(TaskList newList) {
        list.clear();
        if (newList == null) {
            return;
        }
        for (Task task : newList) {
            list.add(task);
        }
    }

    public Result execute(Command cmd) throws Exception{
        return cmd.execute();     
    }
}
