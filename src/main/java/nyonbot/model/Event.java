package nyonbot.model;

import java.time.LocalDateTime;

import nyonbot.Ui;

public class Event extends Task{
    LocalDateTime startTime;
    LocalDateTime endTime;

    public Event(String taskName, LocalDateTime start, LocalDateTime end) {
        super(taskName);
        this.startTime = start;
        this.endTime = end;
    }

    public LocalDateTime[] getEventTime() {
        LocalDateTime[] eventTime = {this.startTime, this.endTime};
        return eventTime;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)",
                this.isDone ? "X" : " ", 
                this.taskName, 
                Ui.showDate(this.startTime), 
                Ui.showDate(this.endTime)
        );
    }
}
