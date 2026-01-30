package bru.exception;

/**
 * The EmptyParmsException class represents an exceptional event
 * where the user supplied no parameters while at least one parameter
 * was expected.
 */
public class EmptyParmsException extends InvalidParmsException {
    public EmptyParmsException(String message) {
        super(message);
    }

    @Override
    public String getDisplayMessage(String subject) {
        return String.format("Bruh... the description of %s cannot be empty.", subject);
    }
}
