package bru.exception;

/**
 * The BruException class is an exception that is specific to the chatbot.
 * Any subexceptions must override the getDisplayMessage() method which
 * determines the error message displayed to the user.
 */
public abstract class BruException extends RuntimeException {
    public BruException(String message) {
        super(message);
    }

    public abstract String getDisplayMessage(String subject);
}
