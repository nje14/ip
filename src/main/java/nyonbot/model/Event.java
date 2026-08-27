package nyonbot.model;

public class Event extends Task{
    String startTime;
    String endTime;

    public Event(String taskName, String start, String end) {
        super(taskName);
        this.startTime = start;
        this.endTime = end;
    }

    public String[] getEventTime() {
        String[] eventTime = {this.startTime, this.endTime};
        return eventTime;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)",
                this.isDone ? "X" : " ", 
                this.taskName, 
                this.startTime, 
                this.endTime
        );
    }
}
