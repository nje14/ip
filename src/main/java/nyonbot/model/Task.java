package nyonbot.model;

/**
 * Represents a task that can be completed or left incomplete
 * 
 * @author nje14
 */
public class Task {
    protected String taskName;
    protected boolean isDone = false;
    
    /**
     * Creates a incomplete task with the specified name
     * 
     * @param taskName
     */
    public Task(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Marks task as completed
     */
    public void completeTask() {
        this.isDone = true;
    }

    /**
     * Marks task as incomplete
     */
    public void uncompleteTask() {
        this.isDone = false;
    }

    /**
     * Returns whether the tasks have the same name
     * 
     * @param task
     * @return
     */
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
