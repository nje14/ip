package nyonbot.model;

import java.time.LocalDateTime;

public class Deadline extends Task{
    LocalDateTime deadline;

    public Deadline(String taskName, LocalDateTime deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", this.isDone ? "X" : " ", this.taskName, this.deadline);
    }
}
