import java.util.ArrayList;
import java.lang.StringBuilder;

/**
 * The TaskList class manages a collection of Task objects.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
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
}
