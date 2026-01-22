public class UnknownCommandException extends BruException {
    public UnknownCommandException(String message) {
        super(message);
    }

    @Override
    public String getDisplayMessage(String subject) {
        return "Bruh... I don't know what you mean.";
    }
}
