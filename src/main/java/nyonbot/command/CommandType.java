package nyonbot.command;

public enum CommandType {
    EXIT("bye"),
    ECHO("echo"),
    NYON("nyon"),
    LIST("list"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    MARK("mark"),
    UNMARK("unmark"),
    UNKNOWN("unknown"),
    DELETE("delete");

    private final String keyword;

    CommandType(String keyword) {
        this.keyword = keyword;
    }

    public String keyword() {
        return this.keyword;
    }

    public static CommandType toCommandType(String keyword) {
        for (CommandType t: values()) {
            if (t.keyword.equalsIgnoreCase(keyword)) {
                return t;
            }
        }
        return UNKNOWN;
    }
}
