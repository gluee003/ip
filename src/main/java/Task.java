/**
 * The Task class encapsulates the state of a task.
 */
public abstract class Task {
    protected String message;
    protected boolean done;

    public Task(String message, boolean done) {
        this.message = message;
        this.done = done;
    }

    public Task(String message) {
        this(message, false);
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    protected abstract String getTaskType();

    protected String getCheckmark() {
        return this.done ? "X" : " ";
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.getTaskType(), this.getCheckmark(), this.message);
    }
}
