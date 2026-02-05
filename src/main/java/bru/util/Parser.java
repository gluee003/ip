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
        Command command = Command.UNKNOWN;
        if (cmd.equals("bye")) {
            command = Command.BYE;
        } else if (cmd.equals("list")) {
            command = Command.LIST;
        } else if (cmd.equals("find")) {
            command = Command.FIND;
        } else if (cmd.equals("mark")) {
            command = Command.MARK;
        } else if (cmd.equals("unmark")) {
            command = Command.UNMARK;
        } else if (cmd.equals("todo")) {
            command = Command.TODO;
        } else if (cmd.equals("deadline")) {
            command = Command.DEADLINE;
        } else if (cmd.equals("event")) {
            command = Command.EVENT;
        } else if (cmd.equals("delete")) {
            command = Command.DELETE;
        }

        return new Pair<>(command, parms);
    }
}
