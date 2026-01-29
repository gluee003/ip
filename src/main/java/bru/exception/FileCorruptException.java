package bru.exception;

public class FileCorruptException extends BruException {
    public FileCorruptException(String message) {
        super(message);
    }

    @Override
    public String getDisplayMessage(String subject) {
        return String.format("Bruh... the saved task list was corrupted.");
    }
}
