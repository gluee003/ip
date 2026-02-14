package bru.object;

import java.util.Stack;

import bru.exception.EmptyHistoryException;

/**
 * Tracks the history of states of a task list.
 */
public class TaskListHistory {
    private Stack<TaskList> history;

    public TaskListHistory() {
        this.history = new Stack<>();
    }

    /**
     * Adds a state of the task list to history
     * @param taskList
     */
    public void pushToHistory(TaskList taskList) {
        this.history.push(taskList.copy());
    }

    /**
     * Returns the most recent task list and removes it from history.
     */
    public TaskList popFromHistory() {
        if (this.history.isEmpty()) {
            throw new EmptyHistoryException(history.toString());
        }
        return history.pop();
    }
}
