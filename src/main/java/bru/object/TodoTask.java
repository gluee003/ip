package bru.object;

/**
 * The TodoTask class represents a general task with no date/time attached to it.
 */
public class TodoTask extends Task {
    public TodoTask(String message, boolean isDone) {
        super(message, isDone);
    }

    public TodoTask(String message) {
        this(message, false);
    }

    @Override
    protected String getTaskType() {
        return "T";
    }

    @Override
    public String[] toRow() {
        return new String[]{"T", this.isDone ? "1" : "0", this.message, "", ""};
    }
}
