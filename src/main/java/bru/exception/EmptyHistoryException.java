package bru.exception;

/**
 * The EmptyHistoryException class represents an exceptional event
 * where the user tries to undo when there was no action to undo.
 */
public class EmptyHistoryException extends BruException {
    public EmptyHistoryException(String message) {
        super(message);
    }

    @Override
    public String getDisplayMessage(String subject) {
        return String.format("Bruh... there was nothing to %s.", subject);
    }
}
