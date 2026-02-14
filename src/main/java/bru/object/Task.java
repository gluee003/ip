package bru.object;

/**
 * The Task class encapsulates the state of a task.
 */
public abstract class Task {
    protected String message;
    protected boolean isDone;

    /**
     * Instantiates a task object.
     *
     * @param message The contents of the task.
     * @param isDone  Whether the task was completed or not.
     */
    public Task(String message, boolean isDone) {
        this.message = message;
        this.isDone = isDone;
    }

    public Task(String message) {
        this(message, false);
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    protected abstract String getTaskType();

    protected String getCheckmark() {
        return this.isDone ? "X" : " ";
    }

    /**
     * Returns current state as a string array, to be used for writing save data to a file.
     *
     * @return String array containing task information.
     */
    public abstract String[] toRow();

    /**
     * Returns a deep copy of the task instance.
     *
     * @return A copy of the task instance.
     */
    public abstract Task copy();

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.getTaskType(), this.getCheckmark(), this.message);
    }
}
