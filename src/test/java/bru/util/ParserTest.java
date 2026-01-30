package bru.util;

import org.junit.jupiter.api.Test;
import bru.command.Command;

import bru.util.Parser;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void parseInputTest_empty() {
        String input = "";
        Command expected1 = Command.UNKNOWN;
        String[] expected2 = new String[0];
        Pair<Command, String[]> p = Parser.parseInput(input);
        assertEquals(expected1, p.getFirst());
        assertTrue(Arrays.equals(expected2, p.getSecond()));
    }

    @Test
    public void parseInputTest_unknown() {
        String input = "sfojsadolfhsdol juafhasdiofh";
        Command expected1 = Command.UNKNOWN;
        String[] expected2 = new String[] {"juafhasdiofh"};
        Pair<Command, String[]> p = Parser.parseInput(input);
        assertEquals(expected1, p.getFirst());
        assertTrue(Arrays.equals(expected2, p.getSecond()));
    }

    @Test
    public void parseInputTest_bye() {
        String input = "bye";
        Command expected1 = Command.BYE;
        String[] expected2 = new String[0];
        Pair<Command, String[]> p = Parser.parseInput(input);
        assertEquals(expected1, p.getFirst());
        assertTrue(Arrays.equals(expected2, p.getSecond()));
    }

    @Test
    public void parseInputTest_byebye() {
        String input = "byebye";
        Command expected1 = Command.UNKNOWN;
        String[] expected2 = new String[0];
        Pair<Command, String[]> p = Parser.parseInput(input);
        assertEquals(expected1, p.getFirst());
        assertTrue(Arrays.equals(expected2, p.getSecond()));
    }

    @Test
    public void parseInputTest_list() {
        String input = "list";
        Command expected1 = Command.LIST;
        String[] expected2 = new String[0];
        Pair<Command, String[]> p = Parser.parseInput(input);
        assertEquals(expected1, p.getFirst());
        assertTrue(Arrays.equals(expected2, p.getSecond()));
    }

    @Test
    public void parseInputTest_find() {
        String input = "find dx";
        Command expected1 = Command.FIND;
        String[] expected2 = new String[] {"dx"};
        Pair<Command, String[]> p = Parser.parseInput(input);
        assertEquals(expected1, p.getFirst());
        assertTrue(Arrays.equals(expected2, p.getSecond()));
    }

    @Test
    public void parseInputTest_mark() {
        String input = "mark 1";
        Command expected1 = Command.MARK;
        String[] expected2 = new String[] {"1"};
        Pair<Command, String[]> p = Parser.parseInput(input);
        assertEquals(expected1, p.getFirst());
        assertTrue(Arrays.equals(expected2, p.getSecond()));
    }

    @Test
    public void parseInputTest_unmark() {
        String input = "unmark 2";
        Command expected1 = Command.UNMARK;
        String[] expected2 = new String[] {"2"};
        Pair<Command, String[]> p = Parser.parseInput(input);
        assertEquals(expected1, p.getFirst());
        assertTrue(Arrays.equals(expected2, p.getSecond()));
    }

    @Test
    public void parseInputTest_todo() {
        String input = "todo 6 7";
        Command expected1 = Command.TODO;
        String[] expected2 = new String[] {"6", "7"};
        Pair<Command, String[]> p = Parser.parseInput(input);
        assertEquals(expected1, p.getFirst());
        assertTrue(Arrays.equals(expected2, p.getSecond()));
    }

    @Test
    public void parseInputTest_deadline() {
        String input = "deadline bruh /by 1970-01-01";
        Command expected1 = Command.DEADLINE;
        String[] expected2 = new String[] {"bruh", "/by", "1970-01-01"};
        Pair<Command, String[]> p = Parser.parseInput(input);
        assertEquals(expected1, p.getFirst());
        assertTrue(Arrays.equals(expected2, p.getSecond()));
    }

    @Test
    public void parseInputTest_event() {
        String input = "event gamba /from 1970-01-01 /to 2000-01-01";
        Command expected1 = Command.EVENT;
        String[] expected2 = new String[] {"gamba", "/from", "1970-01-01", "/to", "2000-01-01"};
        Pair<Command, String[]> p = Parser.parseInput(input);
        assertEquals(expected1, p.getFirst());
        assertTrue(Arrays.equals(expected2, p.getSecond()));
    }

    @Test
    public void parseInputTest_delete() {
        String input = "delete 67";
        Command expected1 = Command.DELETE;
        String[] expected2 = new String[] {"67"};
        Pair<Command, String[]> p = Parser.parseInput(input);
        assertEquals(expected1, p.getFirst());
        assertTrue(Arrays.equals(expected2, p.getSecond()));
    }
}
