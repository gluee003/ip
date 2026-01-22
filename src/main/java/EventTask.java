/**
 * The EventTask class represents a task that starts and ends at some given dates/times.
 */
public class EventTask extends Task {
    protected String start;
    protected String end;

    public EventTask(String message, boolean done, String start, String end) {
        super(message, done);
        this.start = start;
        this.end = end;
    }

    public EventTask(String message, String start, String end) {
        this(message, false, start, end);
    }

    @Override
    protected String getTaskType() {
        return "E";
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)", super.toString(), this.start, this.end);
    }
}
