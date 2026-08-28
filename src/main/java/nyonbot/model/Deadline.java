package nyonbot.model;

import java.time.LocalDateTime;

import nyonbot.Ui;

/**
 * Represents a deadline task
 */
public class Deadline extends Task{
    LocalDateTime deadline;

    /**
     * Creates a Deadline task with the associated name and deadline
     * 
     * @param taskName
     * @param deadline
     */
    public Deadline(String taskName, LocalDateTime deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", this.isDone ? "X" : " ", this.taskName, Ui.showDate(this.deadline));
    }
}
