package nyonbot.command;



public record Command (Commands command, String message) {
    public enum Commands {
        EXIT,
        ECHO,
        NYON,
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        MARK,
        UNMARK,
    } 
}
