package bru.exception;

public class EmptyParmsException extends InvalidParmsException {
    public EmptyParmsException(String message) {
        super(message);
    }

    @Override
    public String getDisplayMessage(String subject) {
        return String.format("Bruh... the description of %s cannot be empty.", subject);
    }
}
