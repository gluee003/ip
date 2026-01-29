public class InvalidDateException extends BruException {
    public InvalidDateException(String message) {
        super(message);
    }

    @Override
    public String getDisplayMessage(String subject) {
        return String.format("Bruh... the date(s) of %s is invalid", subject);
    }
}
