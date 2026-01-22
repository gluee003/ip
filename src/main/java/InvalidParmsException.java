public class InvalidParmsException extends BruException {
    public InvalidParmsException(String message) {
        super(message);
    }

    @Override
    public String getDisplayMessage(String subject) {
        return String.format("Bruh... the description of %s is invalid.", subject);
    }
}
