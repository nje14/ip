package nyonbot.command;

/**
 * An enum class that stores the mappings between command types and its string command
 */
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
    DELETE("delete"),
    WIPE("wipe");

    private final String keyword;

    CommandType(String keyword) {
        this.keyword = keyword;
    }

    public String keyword() {
        return this.keyword;
    }

    /**
     * converts the string command to its enum type
     * 
     * @param keyword command keyword to be parsed
     * @return the <code>CommandType</code> associated with this keyword
     */
    public static CommandType toCommandType(String keyword) {
        for (CommandType t: values()) {
            if (t.keyword.equalsIgnoreCase(keyword)) {
                return t;
            }
        }
        return UNKNOWN;
    }
}
