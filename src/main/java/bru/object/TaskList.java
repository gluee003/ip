package bru.object;

import java.util.ArrayList;
import java.util.List;

import bru.util.Pair;

/**
 * The TaskList class manages a collection of Task objects.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns the list of pairs of tasks, and their positions, whose description matches a given pattern.
     *
     * @param pattern The string pattern to match against
     */
    public ArrayList<Pair<Integer, Task>> findTasks(String pattern) {
        ArrayList<Pair<Integer, Task>> foundTasks = new ArrayList<>();

        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).message.matches(pattern)) {
                foundTasks.add(new Pair<>(i + 1, this.tasks.get(i)));
            }
        }

        return foundTasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes a task from the task list.
     *
     * @param position 1-indexed position of task to be removed
     * @return Task that was removed
     * @throws IndexOutOfBoundsException
     */
    public Task deleteTask(int position) throws IndexOutOfBoundsException {
        Task task = this.tasks.get(position - 1);
        this.tasks.remove(position - 1);
        return task;
    }

    /**
     * Marks a task as completed.
     *
     * @param position 1-indexed position of task to be marked.
     * @return Task that was marked.
     */
    public Task markTask(int position) {
        if (position < 1 || position > this.tasks.size()) {
            return null;
        }
        Task task = this.tasks.get(position - 1);
        task.mark();

        return task;
    }

    /**
     * Unmarks a task as complete.
     *
     * @param position 1-indexed position of task to be unmarked.
     * @return Task that was unmarked.
     */
    public Task unmarkTask(int position) {
        if (position < 1 || position > this.tasks.size()) {
            return null;
        }
        Task task = this.tasks.get(position - 1);
        task.unmark();

        return task;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < this.tasks.size(); i++) {
            sb.append(String.format("%d. %s\n", i + 1, tasks.get(i).toString()));
        }

        return sb.toString();
    }

    /**
     * Returns current states of all tasks as a list of task information,
     * to be used for writing save data to a file.
     *
     * @return List of String[] containing all task information.
     */
    public List<String> toRows() {
        return this.tasks.stream()
                .map(task -> String.join("|", task.toRow()))
                .toList();
    }
}
