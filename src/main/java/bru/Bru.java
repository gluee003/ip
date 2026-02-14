package bru;

import java.nio.file.Path;
import java.nio.file.Paths;

import bru.command.Command;
import bru.command.CommandHandler;
import bru.exception.BruException;
import bru.exception.UnknownCommandException;
import bru.object.TaskList;
import bru.object.TaskListHistory;
import bru.util.FileHandler;
import bru.util.Pair;
import bru.util.Parser;
import bru.util.Ui;

/**
 * The Bru class is the entry point to the chatbot.
 */
public class Bru {
    public static final String NAME = "Bru";
    private static final Path SAVE_FOLDER_PATH = Paths.get("data");
    private static final Path SAVE_FILE_PATH = Bru.SAVE_FOLDER_PATH.resolve("bru.txt");
    private static TaskList taskList;
    private static TaskListHistory taskListHistory;

    /**
     * Initialises the task list by reading from a save file.
     */
    public void initialise() {
        FileHandler.createFolder(Bru.SAVE_FOLDER_PATH);
        FileHandler.createFile(Bru.SAVE_FILE_PATH);
        assert FileHandler.fileExists(Bru.SAVE_FILE_PATH)
                : String.format("Save file at %s does not exist", Bru.SAVE_FILE_PATH);
        Bru.taskList = FileHandler.readFromFile(Bru.SAVE_FILE_PATH);
        Bru.taskListHistory = new TaskListHistory();
    }

    private void undo() {
        Bru.taskList = Bru.taskListHistory.popFromHistory();
    }

    private void recordHistory(TaskList taskList) {
        Bru.taskListHistory.pushToHistory(taskList);
    }

    /**
     * Returns a response to a user's command.
     *
     * @param input
     * @return A boolean indicating whether to exit the program, and the response of the chatbot.
     */
    public Pair<Boolean, String> getResponse(String input) {
        Pair<Command, String[]> pair = Parser.parseInput(input);
        Command command = pair.getFirst();
        String[] parms = pair.getSecond();

        assert Bru.taskList != null : "Task list is null";
        assert command != null : "Command is null";
        assert parms != null : "Parameters is null";

        try {
            switch (command) {
            case BYE:
                return new Pair<>(true, Ui.getGoodbyeMsg());
            case LIST:
                return new Pair<>(false, Ui.getTaskList(Bru.taskList));
            case FIND:
                return new Pair<>(false, CommandHandler.findTask(parms, Bru.taskList));
            case MARK: {
                TaskList prevTaskList = Bru.taskList.copy();
                Pair<Boolean, String> p = new Pair<>(false, CommandHandler.markTask(parms, true, Bru.taskList));
                this.recordHistory(prevTaskList);
                return p;
            }
            case UNMARK: {
                TaskList prevTaskList = Bru.taskList.copy();
                Pair<Boolean, String> p = new Pair<>(false, CommandHandler.markTask(parms, false, Bru.taskList));
                this.recordHistory(prevTaskList);
                return p;
            }
            case TODO: {
                TaskList prevTaskList = Bru.taskList.copy();
                Pair<Boolean, String> p = new Pair<>(false, CommandHandler.addTodoTask(parms, Bru.taskList));
                this.recordHistory(prevTaskList);
                return p;
            }
            case DEADLINE: {
                TaskList prevTaskList = Bru.taskList.copy();
                Pair<Boolean, String> p = new Pair<>(false, CommandHandler.addDeadlineTask(parms, Bru.taskList));
                this.recordHistory(prevTaskList);
                return p;
            }
            case EVENT: {
                TaskList prevTaskList = Bru.taskList.copy();
                Pair<Boolean, String> p = new Pair<>(false, CommandHandler.addEventTask(parms, Bru.taskList));
                this.recordHistory(prevTaskList);
                return p;
            }
            case DELETE: {
                TaskList prevTaskList = Bru.taskList.copy();
                Pair<Boolean, String> p = new Pair<>(false, CommandHandler.deleteTask(parms, Bru.taskList));
                this.recordHistory(prevTaskList);
                return p;
            }
            case UNDO:
                this.undo();
                return new Pair<>(false, Ui.getUndoMessage(Bru.taskList));
            default:
                throw new UnknownCommandException(input);
            }
        } catch (BruException e) {
            return new Pair<>(false, Ui.getErrorMsg(e, command));
        } finally {
            assert FileHandler.fileExists(Bru.SAVE_FILE_PATH)
                    : String.format("Save file at %s does not exist", Bru.SAVE_FILE_PATH);
            FileHandler.writeToFile(SAVE_FILE_PATH, Bru.taskList);
        }
    }
}
