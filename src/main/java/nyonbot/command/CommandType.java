package nyonbot.command;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * An enum class that stores the mappings between command types and its string command
 */
public enum CommandType {
    EXIT("bye", "exit"),
    ECHO("echo", "cat"),
    NYON("nyon"),
    LIST("list", "ls"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    MARK("mark"),
    UNMARK("unmark"),
    UNKNOWN("unknown"),
    DELETE("delete", "del", "rm"),
    FIND("find", "grep");

    private final String mainKeyword;
    private final Set<String> keyword;

    CommandType(String... keywords) {
        this.mainKeyword = keywords.length > 0 ? keywords[0] : null;
        this.keyword = new HashSet<>(Arrays.asList(keywords));
    }

    public String keyword() {
        return this.mainKeyword;
    }

    /**
     * converts the string command to its enum type
     * @param keyword command keyword to be parsed
     * @return the <code>CommandType</code> associated with this keyword
     */
    public static CommandType toCommandType(String keyword) {
        for (CommandType t: values()) {
            if (t.keyword.contains(keyword.toLowerCase())) {
                return t;
            }
        }
        return UNKNOWN;
    }
}
