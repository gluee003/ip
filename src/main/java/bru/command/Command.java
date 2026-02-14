package bru.command;

/**
 * The Command enum lists the possible types of commands supported by the bot.
 */
public enum Command {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete"),
    LIST("list"),
    FIND("find"),
    MARK("mark"),
    UNMARK("unmark"),
    UNDO("undo"),
    BYE("bye"),
    UNKNOWN("unknown");

    private String name;

    Command(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
