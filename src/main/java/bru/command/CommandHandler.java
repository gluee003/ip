package bru.command;

import bru.exception.EmptyParmsException;
import bru.exception.InvalidDateException;
import bru.exception.InvalidParmsException;
import bru.exception.TaskNotFoundException;
import bru.object.*;
import bru.util.Ui;
import bru.util.Utils;
import bru.util.Pair;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * The CommandHandler handles the logic of running user commands.
 */
public class CommandHandler {
    /**
     * Finds tasks whose description matches a user specified pattern.
     *
     * @param parms The parameters supplied by the user
     * @param taskList The task list
     */
    public static void findTask(String[] parms, TaskList taskList) {
        if (parms.length == 0) {
            throw new EmptyParmsException(String.join(" ", parms));
        }
        String value = String.join(" ", parms);
        String pattern = String.format(".*%s.*", value);
        ArrayList<Pair<Integer, Task>> foundTasks = taskList.findTasks(pattern);
        if (foundTasks.isEmpty()) {
            throw new TaskNotFoundException(value);
        }
        Ui.displayFilteredTaskList(foundTasks);
    }

    /**
     * Marks a task in the task list as completed.
     *
     * @param parms The parameters supplied by the user
     * @param isMarked Whether to mark/unmark the task
     * @param taskList The task list
     */
    public static void markTask(String[] parms, boolean isMarked, TaskList taskList) {
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
            Ui.displayTaskMarking(task, isMarked);
        } catch (NumberFormatException e) {
            throw new InvalidParmsException(value);
        }
    }

    /**
     * Adds a task to the task list
     *
     * @param task The task to be added
     * @param taskList The task list
     */
    public static void addTask(Task task, TaskList taskList) {
        taskList.addTask(task);
        Ui.displayTaskAdding(task, taskList);
    }

    /**
     * Adds a todo task to the task list
     *
     * @param parms The parameters supplied by the user
     * @param taskList The task list
     */
    public static void addTodoTask(String[] parms, TaskList taskList) {
        if (parms.length == 0) {
            throw new EmptyParmsException(String.join(" ", parms));
        }
        String msg = String.join(" ", parms);
        Task task = new TodoTask(msg);
        CommandHandler.addTask(task, taskList);
    }

    /**
     * Adds a deadline task to the task list
     *
     * @param parms The parameters supplied by the user
     * @param taskList The task list
     */
    public static void addDeadlineTask(String[] parms, TaskList taskList) {
        if (parms.length == 0) {
            throw new EmptyParmsException(String.join(" ", parms));
        }
        int delimiterPosition = Utils.findInArr(parms, "/by");
        if (delimiterPosition == -1 || delimiterPosition == 0
                || delimiterPosition == parms.length - 1) {
            throw new InvalidParmsException(String.join(" ",parms));
        }
        String msg = String.join(" ",
                Arrays.copyOfRange(parms, 0,delimiterPosition));
        String deadlineStr = String.join(" ",
                Arrays.copyOfRange(parms,delimiterPosition + 1,parms.length));

        try {
            LocalDate deadline = LocalDate.parse(deadlineStr);
            Task task = new DeadlineTask(msg, deadline);
            CommandHandler.addTask(task, taskList);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException(String.join(" ", parms));
        }
    }

    /**
     * Adds a event task to the task list
     *
     * @param parms The parameters supplied by the user
     * @param taskList The task list
     */
    public static void addEventTask(String[] parms, TaskList taskList) {
        if (parms.length == 0) {
            throw new EmptyParmsException(String.join(" ", parms));
        }
        int startPosition = Utils.findInArr(parms, "/from");
        int endPosition = Utils.findInArr(parms, "/to");
        if (startPosition == -1 || endPosition == -1 || startPosition >= endPosition
                || startPosition == 0 || startPosition == parms.length - 1
                || endPosition == 0 || endPosition == parms.length - 1
                || startPosition + 1 == endPosition) {
            throw new InvalidParmsException(String.join(" ",parms));
        }
        String msg = String.join(" ",
                Arrays.copyOfRange(parms, 0,startPosition));
        String startStr = String.join(" ",
                Arrays.copyOfRange(parms, startPosition + 1,endPosition));
        String endStr = String.join(" ",
                Arrays.copyOfRange(parms, endPosition + 1,parms.length));
        try {
            LocalDate start = LocalDate.parse(startStr);
            LocalDate end = LocalDate.parse(endStr);
            Task task = new EventTask(msg, start, end);
            CommandHandler.addTask(task, taskList);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException(String.join(" ", parms));
        }
    }

    /**
     * Removes a task from the task list.
     *
     * @param parms The parameters supplied by the user
     * @param taskList The task list
     */
    public static void deleteTask(String[] parms, TaskList taskList) {
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

            Ui.displayTaskDeleting(task, taskList);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidParmsException(value);
        }
    }
}
