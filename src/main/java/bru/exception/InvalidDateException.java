package bru.exception;

/**
 * The InvalidDateException class represents an exceptional event
 * where user supplied dates are invalid and cannot be parsed.
 */
public class InvalidDateException extends BruException {
    public InvalidDateException(String message) {
        super(message);
    }

    @Override
    public String getDisplayMessage(String subject) {
        return String.format("Bruh... the date(s) of %s is invalid", subject);
    }
}
