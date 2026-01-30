package bru.exception;

/**
 * The FileCorruptException class represents an exceptional event
 * where the save file for tasks is not in a format that is readable,
 * perhaps due to being edited/corrupted.
 */
public class FileCorruptException extends BruException {
    public FileCorruptException(String message) {
        super(message);
    }

    @Override
    public String getDisplayMessage(String subject) {
        return String.format("Bruh... the saved task list was corrupted.");
    }
}
