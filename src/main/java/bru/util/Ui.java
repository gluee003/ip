package bru.util;

import bru.object.Task;
import bru.object.TaskList;

import bru.command.Command;

import bru.exception.BruException;

import java.util.ArrayList;

/**
 * The Ui class handles displaying output to the user.
 */
public class Ui {
    /**
     * Prints a welcome message, introducing the chatbot's name.
     *
     * @param name The chatbot's name
     */
    public static void displayWelcomeMsg(String name) {
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?\n");
    }

    /**
     * Prints a goodbye message, indicating the end of the session.
     */
    public static void displayGoodbyeMsg() {
        System.out.println("See you next time bruh.");
    }

    /**
     * Prints every task in the task list.
     *
     * @param taskList The task list.
     */
    public static void displayTaskList(TaskList taskList) {
        System.out.println(taskList);
    }

    /**
     * Prints a filtered task list.
     *
     * @param tasks The filtered tasks with their indices.
     */
    public static void displayFilteredTaskList(ArrayList<Pair<Integer, Task>> tasks) {
        String successMsg = "Here are the matching tasks in your list:";
        System.out.println(successMsg);
        StringBuilder sb = new StringBuilder();

        for (Pair<Integer, Task> p : tasks) {
            sb.append(String.format("%d. %s\n", p.getFirst(),p.getSecond()));
        }

        System.out.println(sb.toString());
    }

    /**
     * Prints a task that was marked/unmarked.
     *
     * @param task The task
     * @param isMarked Whether the task was marked/unmarked
     */
    public static void displayTaskMarking(Task task, boolean isMarked) {
        String markedMsg = "Nice! I've marked this task as done:";
        String unmarkedMsg = "OK, I've marked this task as not done yet:";
        System.out.println(isMarked ? markedMsg : unmarkedMsg);
        System.out.println(task);
    }

    /**
     * Prints a task that was added.
     * Also prints the size of task list.
     *
     * @param task The task that as added
     * @param taskList The task list
     */
    public static void displayTaskAdding(Task task, TaskList taskList) {
        System.out.println(String.format("added: %s", task));
        System.out.println(String.format("Now you have %d tasks in the list.", taskList.size()));
    }

    /**
     * Prints a task that was removed.
     * Also prints the size of task list.
     *
     * @param task The task that as removed
     * @param taskList The task list
     */
    public static void displayTaskDeleting(Task task, TaskList taskList) {
        System.out.println(String.format("Noted. I've removed this task: %s", task));
        System.out.println(String.format("Now you have %d tasks in the list.", taskList.size()));
    }

    /**
     * Prints an error message based on an exception that was thrown.
     *
     * @param e The exception
     * @param command The command that caused the exception
     */
    public static void displayErrorMsg(BruException e, Command command) {
        System.out.println(e.getDisplayMessage(command.toString()));
    }
}
