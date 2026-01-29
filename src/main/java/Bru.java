import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class Bru {
    public static final String NAME = "Bru";
    private static final Path SAVE_FOLDER_PATH = Paths.get("data");
    private static final Path SAVE_FILE_PATH = Bru.SAVE_FOLDER_PATH.resolve("bru.txt");
    private static TaskList taskList;

    private static Pair<Command, String[]> parseInput(String input) {
        String[] splitInput = input.split(" ");
        if (splitInput.length == 0) {
            return new Pair<>(Command.UNKNOWN, splitInput);
        }

        String cmd = splitInput[0];
        String[] parms = Arrays.copyOfRange(splitInput, 1, splitInput.length);
        Command command = Command.UNKNOWN;
        if (cmd.equals("bye")) {
            command = Command.BYE;
        } else if (cmd.equals("list")) {
            command = Command.LIST;
        } else if (cmd.equals("mark")) {
            command = Command.MARK;
        } else if (cmd.equals("unmark")) {
            command = Command.UNMARK;
        } else if (cmd.equals("todo")) {
            command = Command.TODO;
        } else if (cmd.equals("deadline")) {
            command = Command.DEADLINE;
        } else if (cmd.equals("event")) {
            command = Command.EVENT;
        } else if (cmd.equals("delete")) {
            command = Command.DELETE;
        }

        return new Pair<>(command, parms);
    }

    private static void displayWelcomeMsg() {
        System.out.println("Hello! I'm " + Bru.NAME);
        System.out.println("What can I do for you?\n");
    }

    private static void displayGoodbyeMsg() {
        System.out.println("See you next time bruh.");
    }

    private static void displayTaskList() {
        System.out.println(Bru.taskList);
    }

    private static void markTask(String[] parms, boolean mark) {
        if (parms.length == 0) {
            throw new EmptyParmsException(String.join(" ", parms));
        }
        String markedMsg = "Nice! I've marked this task as done:";
        String unmarkedMsg = "OK, I've marked this task as not done yet:";
        String value = String.join(" ", parms);
        try {
            int position = Integer.valueOf(value);
            Task task = mark ? Bru.taskList.markTask(position) : Bru.taskList.unmarkTask(position);

            if (task == null) {
                throw new TaskNotFoundException(value);
            }

            if (mark) {
                System.out.println(markedMsg);
            } else {
                System.out.println(unmarkedMsg);
            }
            System.out.println(task);
        } catch (NumberFormatException e) {
            throw new InvalidParmsException(value);
        }
    }

    private static void addTask(Task task) {
        Bru.taskList.addTask(task);
        System.out.println(String.format("added: %s", task));
        System.out.println(String.format("Now you have %d tasks in the list.", Bru.taskList.size()));
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
        String deadline = String.join(" ",
                Arrays.copyOfRange(parms,delimiterPosition + 1,parms.length));
        Task task = new DeadlineTask(msg, deadline);
        Bru.addTask(task);
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
        String start = String.join(" ",
                Arrays.copyOfRange(parms, startPosition + 1,endPosition));
        String end = String.join(" ",
                Arrays.copyOfRange(parms, endPosition + 1,parms.length));
        Task task = new EventTask(msg, start, end);
        Bru.addTask(task);
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

            System.out.println(String.format("Noted. I've removed this task: %s", task));
            System.out.println(String.format("Now you have %d tasks in the list.", Bru.taskList.size()));
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidParmsException(value);
        }
    }

    public static void main(String[] args) {
        Bru.displayWelcomeMsg();
        Scanner scanner = new Scanner(System.in);
        boolean isChatting = true;

        FileHandler.createFolder(Bru.SAVE_FOLDER_PATH);
        FileHandler.createFile(Bru.SAVE_FILE_PATH);
        Bru.taskList = FileHandler.readFromFile(Bru.SAVE_FILE_PATH);

        while (isChatting) {
            String input = scanner.nextLine();
            Pair<Command, String[]> pair = Bru.parseInput(input);
            Command command = pair.getFirst();
            String[] parms = pair.getSecond();
            try {
                switch (command) {
                case BYE:
                    isChatting = false;
                    break;
                case LIST:
                    Bru.displayTaskList();
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
                System.out.println(e.getDisplayMessage(command.toString()));
            }
            FileHandler.writeToFile(SAVE_FILE_PATH, Bru.taskList);
        }

        Bru.displayGoodbyeMsg();
    }
}
