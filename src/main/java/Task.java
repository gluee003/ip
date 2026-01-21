/**
 * The Task class encapsulates the state of a task.
 */
public class Task {
    private String message;

    public Task(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
