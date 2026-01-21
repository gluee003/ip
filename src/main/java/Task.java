import java.util.function.Supplier;

/**
 * The Task class encapsulates the state of a task.
 */
public class Task {
    private String message;
    private boolean done;
    private final Supplier<String> checkmark = () -> this.done ? "X" : " ";

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

    @Override
    public String toString() {
        return String.format("[%s] %s", this.checkmark.get(), this.message);
    }
}
