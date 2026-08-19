package nyonbot.model;

public class Task {
    protected String taskName;
    protected boolean done = false;
    
    public Task(String taskName) {
        this.taskName = taskName;
    }

    public void completeTask() {
        this.done = true;
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", done ? "X" : " ", this.taskName);
    }

}
