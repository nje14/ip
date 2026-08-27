package nyonbot.model;

public class Task {
    protected String taskName;
    protected boolean isDone = false;
    
    public Task(String taskName) {
        this.taskName = taskName;
    }

    public void completeTask() {
        this.isDone = true;
    }

    public void uncompleteTask() {
        this.isDone = false;
    }

    public boolean isSameTask(String task) {
        return this.taskName.equals(task);
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getName() {
        return this.taskName;
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", isDone ? "X" : " ", this.taskName);
    }

}
