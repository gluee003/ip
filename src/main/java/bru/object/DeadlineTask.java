package bru.object;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The DeadlineTask class represents a task that must be completed by a given date/time.
 */
public class DeadlineTask extends Task {
    protected LocalDate deadline;
    private String DATE_TIME_FORMAT_PATTERN = "MMM d yyyy";

    /**
     * Instantiates a task with a deadline.
     *
     * @param message  The contents of the task.
     * @param isDone   Whether the task is completed or not.
     * @param deadline The deadline to complete the task by.
     */
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
        return date.format(DateTimeFormatter.ofPattern(this.DATE_TIME_FORMAT_PATTERN));
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), this.formatDate(this.deadline));
    }

    @Override
    public String[] toRow() {
        return new String[]{"D", this.isDone ? "1" : "0", this.message, this.deadline.toString(), ""};
    }
}
