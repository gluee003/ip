package bru.exception;

/**
 * The TaskNotFoundException class represents an exceptional event
 * where a task to be marked/unmarked/deleted was not found in the task list.
 */
public class TaskNotFoundException extends BruException {
    public TaskNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getDisplayMessage(String subject) {
        return String.format("Bruh... the task to %s could not be found.", subject);
    }
}
