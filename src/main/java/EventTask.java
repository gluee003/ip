import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The EventTask class represents a task that starts and ends at some given dates/times.
 */
public class EventTask extends Task {
    protected LocalDate start;
    protected LocalDate end;

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
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)", super.toString(),
                this.formatDate(this.start), this.formatDate(this.end));
    }

    @Override
    public String[] toRow() {
        return new String[] {"E", this.isDone ? "1" : "0", this.message, this.start.toString(), this.end.toString()};
    }
}
