package bru.command;

/**
 * The Result class encapsulates the information associated with the execution of a command.
 */
public class Result {
    private boolean isBye;
    private boolean isError;
    private String response;

    /**
     * Instantiates a Result instance.
     *
     * @param isBye Whether to exit the program
     * @param isError Whether the execution of the command resulted in an error
     * @param response The chatbot's response to the user command
     */
    public Result(boolean isBye, boolean isError, String response) {
        this.isBye = isBye;
        this.isError = isError;
        this.response = response;
    }

    public Result(boolean isBye, String response) {
        this(isBye, false, response);
    }

    public Result(String response) {
        this(false, false, response);
    }

    public boolean getIsBye() {
        return this.isBye;
    }

    public boolean getIsError() {
        return this.isError;
    }

    public String getResponse() {
        return this.response;
    }
}
