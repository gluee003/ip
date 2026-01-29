package bru.exception;

public abstract class BruException extends RuntimeException {
    public BruException(String message) {
        super(message);
    }

    public abstract String getDisplayMessage(String subject);
}
