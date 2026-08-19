package nyonbot.model;

public class Event extends Task{
    String startTime;
    String endTime;

    public Event(String taskName, String start, String end) {
        super(taskName);
        this.startTime = start;
        this.endTime = end;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)",
            this.done ? "X" : " ", 
            this.taskName, 
            this.startTime, 
            this.endTime
        );
    }
}
