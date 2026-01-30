package bru.exception;

/**
 * The UnknownCommandException class represents an exceptional event
 * where the command, that is, the first parameter supplied by the user,
 * is not recognised by the chatbot.
 */
public class UnknownCommandException extends BruException {
    public UnknownCommandException(String message) {
        super(message);
    }

    @Override
    public String getDisplayMessage(String subject) {
        return "Bruh... I don't know what you mean.";
    }
}
