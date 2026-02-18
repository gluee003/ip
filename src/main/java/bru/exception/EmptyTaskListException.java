package bru.exception;

/**
 * The EmptyTaskListException class represents an exceptional event
 * where the user tries to display an empty task list.
 */
public class EmptyTaskListException extends BruException {
    public EmptyTaskListException(String message) {
        super(message);
    }

    @Override
    public String getDisplayMessage(String subject) {
        return String.format("Bruh... there are no tasks to %s.", subject);
    }
}
