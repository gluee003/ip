/**
 * The DeadlineTask class represents a task that must be completed by a given date/time.
 */
public class DeadlineTask extends Task {
    protected String deadline;

    public DeadlineTask(String message, boolean isDone, String deadline) {
        super(message, isDone);
        this.deadline = deadline;
    }

    public DeadlineTask(String message, String deadline) {
        this(message, false, deadline);
    }

    @Override
    protected String getTaskType() {
        return "D";
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), this.deadline);
    }

    @Override
    public String[] toRow() {
        return new String[] {"D", this.isDone ? "1" : "0", this.message, this.deadline, ""};
    }
}
