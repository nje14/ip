package nyonbot.model;

public class Deadline extends Task{
    String deadline;

    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D][%s]  %s (by: %s)", this.done ? "X" : " ", this.taskName, this.deadline);
    }
}
