package bru.util;

import java.util.Arrays;

import bru.command.Command;

/**
 * The Parser class handles the parsing of raw user input into bot commands.
 */
public class Parser {
    /**
     * Parses the user's raw input and returns a pair of command and additional parameters.
     *
     * @param input The user's input
     * @return A Pair &lt;Command, String[]&gt; which encapsulates the type of command
     *     and the additional parameters which may be relevant to the command.
     */
    public static Pair<Command, String[]> parseInput(String input) {
        String[] splitInput = input.split(" ");
        if (splitInput.length == 0) {
            return new Pair<>(Command.UNKNOWN, splitInput);
        }

        String cmd = splitInput[0];
        String[] parms = Arrays.copyOfRange(splitInput, 1, splitInput.length);
        Command command = switch (cmd) {
            case "bye" -> Command.BYE;
            case "list" -> Command.LIST;
            case "find" -> Command.FIND;
            case "mark" -> Command.MARK;
            case "unmark" -> Command.UNMARK;
            case "todo" -> Command.TODO;
            case "deadline" -> Command.DEADLINE;
            case "event" -> Command.EVENT;
            case "delete" -> Command.DELETE;
            case "undo" -> Command.UNDO;
            default -> Command.UNKNOWN;
        };

        return new Pair<>(command, parms);
    }
}
