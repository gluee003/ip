/**
 * The Command enum lists the possible types of commands supported by the bot.
 */
public enum Command {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
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
