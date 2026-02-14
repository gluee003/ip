package bru.util;

import java.util.ArrayList;

import bru.command.Command;
import bru.exception.BruException;
import bru.object.Task;
import bru.object.TaskList;

/**
 * The Ui class handles formatting output to be displayed to the user.
 */
public class Ui {
    /**
     * Formats a welcome message, introducing the chatbot's name.
     *
     * @param name The chatbot's name
     */
    public static String getWelcomeMsg(String name) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Yo, I'm %s.\n", name));
        sb.append("What can a brother do for you?");
        return sb.toString();
    }

    /**
     * Formats a goodbye message, indicating the end of the session.
     */
    public static String getGoodbyeMsg() {
        return "See you next time bruh.";
    }

    /**
     * Formats task list to be displayed.
     *
     * @param taskList The task list.
     */
    public static String getTaskList(TaskList taskList) {
        return taskList.toString();
    }

    /**
     * Formats the filtered task list to be displayed.
     *
     * @param tasks The filtered tasks with their indices.
     */
    public static String getFilteredTaskList(ArrayList<Pair<Integer, Task>> tasks) {
        String successMsg = "Okay bruh, here are the matching tasks in your list:\n";
        return tasks.stream()
                .map(p -> String.format("%d. %s\n", p.getFirst(), p.getSecond()))
                .reduce(successMsg, (acc, s) -> acc + s);
    }

    /**
     * Formats the marked/unmarked task to be displayed.
     *
     * @param task     The task
     * @param isMarked Whether the task was marked/unmarked
     */
    public static String getTaskMarking(Task task, boolean isMarked) {
        String markedMsg = "Nice one bruh. I've marked this task as done:\n";
        String unmarkedMsg = "Bruh moment. I've marked this task as not done yet:\n";
        StringBuilder sb = new StringBuilder();
        sb.append(isMarked ? markedMsg : unmarkedMsg);
        sb.append(task);
        return sb.toString();
    }

    /**
     * Formats the task to be added, along with the size of the task list.
     *
     * @param task     The task that as added
     * @param taskList The task list
     */
    public static String getTaskAdding(Task task, TaskList taskList) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("added: %s\n", task));
        sb.append(String.format("Now you have %d tasks in the list.", taskList.size()));
        return sb.toString();
    }

    /**
     * Formats the task to be added, along with the size of the task list.
     *
     * @param task     The task that as removed
     * @param taskList The task list
     */
    public static String getTaskDeleting(Task task, TaskList taskList) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Okay bruh, I've removed this task: %s\n", task));
        sb.append(String.format("Now you have %d tasks in the list.", taskList.size()));
        return sb.toString();
    }

    public static String getUndoMessage(TaskList taskList) {
        StringBuilder sb = new StringBuilder();
        sb.append("Fine bruh. I'll undo your last command.\n");
        sb.append("Your task list now looks like this:\n");
        sb.append(taskList.toString());
        return sb.toString();
    }

    /**
     * Formats an error message based on the exception that was thrown.
     *
     * @param e       The exception
     * @param command The command that caused the exception
     */
    public static String getErrorMsg(BruException e, Command command) {
        return e.getDisplayMessage(command.toString());
    }
}
