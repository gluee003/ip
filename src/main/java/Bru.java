import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;
import java.time.LocalDate;

public class Bru {
    public static final String NAME = "Bru";
    private static final Path SAVE_FOLDER_PATH = Paths.get("data");
    private static final Path SAVE_FILE_PATH = Bru.SAVE_FOLDER_PATH.resolve("bru.txt");
    private static TaskList taskList;

    private static void markTask(String[] parms, boolean isMarked) {
        if (parms.length == 0) {
            throw new EmptyParmsException(String.join(" ", parms));
        }
        String value = String.join(" ", parms);
        try {
            int position = Integer.valueOf(value);
            Task task = isMarked ? Bru.taskList.markTask(position) : Bru.taskList.unmarkTask(position);

            if (task == null) {
                throw new TaskNotFoundException(value);
            }
            Ui.displayTaskMarking(task, isMarked);
        } catch (NumberFormatException e) {
            throw new InvalidParmsException(value);
        }
    }

    private static void addTask(Task task) {
        Bru.taskList.addTask(task);
        Ui.displayTaskAdding(task, Bru.taskList);
    }

    private static void addTodoTask(String[] parms) {
        if (parms.length == 0) {
            throw new EmptyParmsException(String.join(" ", parms));
        }
        String msg = String.join(" ", parms);
        Task task = new TodoTask(msg);
        Bru.addTask(task);
    }

    private static void addDeadlineTask(String[] parms) {
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
            Bru.addTask(task);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException(String.join(" ", parms));
        }
    }

    private static void addEventTask(String[] parms) {
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
            Bru.addTask(task);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException(String.join(" ", parms));
        }
    }

    private static void deleteTask(String[] parms) {
        if (parms.length == 0) {
            throw new EmptyParmsException(String.join(" ", parms));
        }
        String value = String.join(" ", parms);
        try {
            int position = Integer.valueOf(value);
            Task task = Bru.taskList.deleteTask(position);

            if (task == null) {
                throw new TaskNotFoundException(value);
            }

            Ui.displayTaskDeleting(task, Bru.taskList);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidParmsException(value);
        }
    }

    public static void main(String[] args) {
        Ui.displayWelcomeMsg(Bru.NAME);
        Scanner scanner = new Scanner(System.in);
        boolean isChatting = true;

        FileHandler.createFolder(Bru.SAVE_FOLDER_PATH);
        FileHandler.createFile(Bru.SAVE_FILE_PATH);
        Bru.taskList = FileHandler.readFromFile(Bru.SAVE_FILE_PATH);

        while (isChatting) {
            String input = scanner.nextLine();
            Pair<Command, String[]> pair = Parser.parseInput(input);
            Command command = pair.getFirst();
            String[] parms = pair.getSecond();
            try {
                switch (command) {
                case BYE:
                    isChatting = false;
                    break;
                case LIST:
                    Ui.displayTaskList(Bru.taskList);
                    break;
                case MARK:
                    Bru.markTask(parms, true);
                    break;
                case UNMARK:
                    Bru.markTask(parms, false);
                    break;
                case TODO:
                    Bru.addTodoTask(parms);
                    break;
                case DEADLINE:
                    Bru.addDeadlineTask(parms);
                    break;
                case EVENT:
                    Bru.addEventTask(parms);
                    break;
                case DELETE:
                    Bru.deleteTask(parms);
                    break;
                default:
                    throw new UnknownCommandException(input);
                }
            } catch (BruException e) {
                Ui.displayErrorMsg(e, command);
            }
            FileHandler.writeToFile(SAVE_FILE_PATH, Bru.taskList);
        }

        Ui.displayGoodbyeMsg();
    }
}
