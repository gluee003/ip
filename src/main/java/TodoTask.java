/**
 * The TodoTask class represents a general task with no date/time attached to it.
 */
public class TodoTask extends Task {
    public TodoTask(String message, boolean done) {
        super(message, done);
    }

    public TodoTask(String message) {
        this(message, false);
    }
    @Override
    protected String getTaskType() {
        return "T";
    }
}
