package bru.object;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The EventTask class represents a task that starts and ends at some given dates/times.
 */
public class EventTask extends Task {
    private static final String DATE_TIME_FORMAT_PATTERN = "MMM d yyyy";
    protected LocalDate start;
    protected LocalDate end;

    /**
     * Instantiates a task with a start and end date.
     *
     * @param message The contents of the task.
     * @param isDone  Whether the task was completed or not.
     * @param start   The start date.
     * @param end     The end date.
     */
    public EventTask(String message, boolean isDone, LocalDate start, LocalDate end) {
        super(message, isDone);
        this.start = start;
        this.end = end;
    }

    public EventTask(String message, LocalDate start, LocalDate end) {
        this(message, false, start, end);
    }

    @Override
    protected String getTaskType() {
        return "E";
    }

    private String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern(EventTask.DATE_TIME_FORMAT_PATTERN));
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)", super.toString(),
                this.formatDate(this.start), this.formatDate(this.end));
    }

    @Override
    public String[] toRow() {
        return new String[]{"E", this.isDone ? "1" : "0", this.message, this.start.toString(), this.end.toString()};
    }
}
