package bru.object;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The DeadlineTask class represents a task that must be completed by a given date/time.
 */
public class DeadlineTask extends Task {
    protected LocalDate deadline;

    public DeadlineTask(String message, boolean isDone, LocalDate deadline) {
        super(message, isDone);
        this.deadline = deadline;
    }

    public DeadlineTask(String message, LocalDate deadline) {
        this(message, false, deadline);
    }

    @Override
    protected String getTaskType() {
        return "D";
    }

    private String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), this.formatDate(this.deadline));
    }

    @Override
    public String[] toRow() {
        return new String[] {"D", this.isDone ? "1" : "0", this.message, this.deadline.toString(), ""};
    }
}
