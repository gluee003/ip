package bru.exception;

/**
 * The InvalidParmsException class represents an exceptional event
 * where the user supplied parameters to a command in an invalid format.
 */
public class InvalidParmsException extends BruException {
    public InvalidParmsException(String message) {
        super(message);
    }

    @Override
    public String getDisplayMessage(String subject) {
        return String.format("Bruh... the description of %s is invalid.", subject);
    }
}
