package bru.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

import bru.exception.EmptyParmsException;
import bru.exception.InvalidDateException;
import bru.exception.InvalidParmsException;
import bru.exception.TaskNotFoundException;
import bru.object.DeadlineTask;
import bru.object.EventTask;
import bru.object.Task;
import bru.object.TaskList;
import bru.object.TodoTask;
import bru.util.Pair;
import bru.util.Ui;
import bru.util.Utils;

/**
 * The CommandHandler handles the logic of running user commands.
 */
public class CommandHandler {
    private static final String DEADLINE_DELIMITER = "/by";
    private static final String EVENT_START_DELIMITER = "/from";
    private static final String EVENT_END_DELIMITER = "/to";

    /**
     * Finds tasks whose description matches a user specified pattern.
     *
     * @param parms    The parameters supplied by the user
     * @param taskList The task list
     * @return The output of tasks to be displayed.
     */
    public static String findTask(String[] parms, TaskList taskList) {
        if (parms.length == 0) {
            throw new EmptyParmsException(String.join(" ", parms));
        }
        String value = String.join(" ", parms);
        String pattern = String.format(".*%s.*", value);
        ArrayList<Pair<Integer, Task>> foundTasks = taskList.findTasks(pattern);
        if (foundTasks.isEmpty()) {
            throw new TaskNotFoundException(value);
        }
        return Ui.getFilteredTaskList(foundTasks);
    }

    /**
     * Marks a task in the task list as completed.
     *
     * @param parms    The parameters supplied by the user
     * @param isMarked Whether to mark/unmark the task
     * @param taskList The task list
     * @return The output of marked task to be displayed.
     */
    public static String markTask(String[] parms, boolean isMarked, TaskList taskList) {
        if (parms.length == 0) {
            throw new EmptyParmsException(String.join(" ", parms));
        }
        String value = String.join(" ", parms);
        try {
            int position = Integer.valueOf(value);
            Task task = isMarked ? taskList.markTask(position) : taskList.unmarkTask(position);

            if (task == null) {
                throw new TaskNotFoundException(value);
            }
            return Ui.getTaskMarking(task, isMarked);
        } catch (NumberFormatException e) {
            throw new InvalidParmsException(value);
        }
    }

    /**
     * Adds a task to the task list
     *
     * @param task     The task to be added
     * @param taskList The task list
     */
    public static String addTask(Task task, TaskList taskList) {
        taskList.addTask(task);
        return Ui.getTaskAdding(task, taskList);
    }

    /**
     * Adds a todo task to the task list
     *
     * @param parms    The parameters supplied by the user
     * @param taskList The task list
     */
    public static String addTodoTask(String[] parms, TaskList taskList) {
        if (parms.length == 0) {
            throw new EmptyParmsException(String.join(" ", parms));
        }
        String msg = String.join(" ", parms);
        Task task = new TodoTask(msg);
        return CommandHandler.addTask(task, taskList);
    }

    private static boolean isValidDeadlineDelimiter(int delimiterPosition, int parmsLength) {
        boolean isFound = delimiterPosition != -1;
        boolean isNotFirst = delimiterPosition != 0;
        boolean isNotLast = delimiterPosition != parmsLength - 1;
        return isFound && isNotFirst && isNotLast;
    }

    /**
     * Adds a deadline task to the task list
     *
     * @param parms    The parameters supplied by the user
     * @param taskList The task list
     */
    public static String addDeadlineTask(String[] parms, TaskList taskList) {
        if (parms.length == 0) {
            throw new EmptyParmsException(String.join(" ", parms));
        }
        int delimiterPosition = Utils.findInArr(parms, CommandHandler.DEADLINE_DELIMITER);
        if (!CommandHandler.isValidDeadlineDelimiter(delimiterPosition, parms.length)) {
            throw new InvalidParmsException(String.join(" ", parms));
        }
        String msg = String.join(" ",
                Arrays.copyOfRange(parms, 0, delimiterPosition));
        String deadlineStr = String.join(" ",
                Arrays.copyOfRange(parms, delimiterPosition + 1, parms.length));

        try {
            LocalDate deadline = LocalDate.parse(deadlineStr);
            Task task = new DeadlineTask(msg, deadline);
            return CommandHandler.addTask(task, taskList);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException(String.join(" ", parms));
        }
    }

    private static boolean isValidEventDelimiter(int startPosition, int endPosition, int parmsLength) {
        boolean isStartFound = startPosition != -1;
        boolean isStartNotFirst = startPosition != 0;
        boolean isStartNotLast = startPosition != parmsLength - 1;
        boolean isStartValid = isStartFound && isStartNotFirst && isStartNotLast;

        boolean isEndFound = endPosition != -1;
        boolean isEndNotFirst = endPosition != 0;
        boolean isEndNotLast = endPosition != parmsLength - 1;
        boolean isEndValid = isEndFound && isEndNotFirst && isEndNotLast;

        boolean isNotInverted = endPosition > startPosition;

        return isStartValid && isEndValid && isNotInverted;
    }

    /**
     * Adds a event task to the task list
     *
     * @param parms    The parameters supplied by the user
     * @param taskList The task list
     */
    public static String addEventTask(String[] parms, TaskList taskList) {
        if (parms.length == 0) {
            throw new EmptyParmsException(String.join(" ", parms));
        }
        int startPosition = Utils.findInArr(parms, CommandHandler.EVENT_START_DELIMITER);
        int endPosition = Utils.findInArr(parms, CommandHandler.EVENT_END_DELIMITER);
        if (!CommandHandler.isValidEventDelimiter(startPosition, endPosition, parms.length)) {
            throw new InvalidParmsException(String.join(" ", parms));
        }
        String msg = String.join(" ",
                Arrays.copyOfRange(parms, 0, startPosition));
        String startStr = String.join(" ",
                Arrays.copyOfRange(parms, startPosition + 1, endPosition));
        String endStr = String.join(" ",
                Arrays.copyOfRange(parms, endPosition + 1, parms.length));
        try {
            LocalDate start = LocalDate.parse(startStr);
            LocalDate end = LocalDate.parse(endStr);
            Task task = new EventTask(msg, start, end);
            return CommandHandler.addTask(task, taskList);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException(String.join(" ", parms));
        }
    }

    /**
     * Removes a task from the task list.
     *
     * @param parms    The parameters supplied by the user
     * @param taskList The task list
     */
    public static String deleteTask(String[] parms, TaskList taskList) {
        if (parms.length == 0) {
            throw new EmptyParmsException(String.join(" ", parms));
        }
        String value = String.join(" ", parms);
        try {
            int position = Integer.valueOf(value);
            Task task = taskList.deleteTask(position);

            if (task == null) {
                throw new TaskNotFoundException(value);
            }

            return Ui.getTaskDeleting(task, taskList);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidParmsException(value);
        }
    }
}
