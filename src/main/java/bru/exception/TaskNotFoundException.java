package bru.exception;

public class TaskNotFoundException extends BruException {
    public TaskNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getDisplayMessage(String subject) {
        return String.format("Bruh... the task to %s could not be found.", subject);
    }
}
