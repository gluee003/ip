import java.util.Arrays;
import java.util.Scanner;

public class Bru {
    public static final String NAME = "Bru";
    private static TaskList taskList = new TaskList();

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
        }

        return new Pair<>(command, parms);
    }

    private static void displayWelcomeMsg() {
        System.out.println("Hello! I'm " + Bru.NAME);
        System.out.println("What can I do for you?\n");
    }

    private static void displayUnknownMsg() {
        System.out.println("Bruh, I don't know what that means.");
    }

    private static void displayGoodbyeMsg() {
        System.out.println("See you next time bruh.");
    }

    private static void displayTaskList() {
        System.out.println(Bru.taskList);
    }

    private static void markTask(String[] parms, boolean mark) {
        String markedMsg = "Nice! I've marked this task as done:";
        String unmarkedMsg = "OK, I've marked this task as not done yet:";
        String value = String.join(" ", parms);
        int position = Integer.valueOf(value);
        Task task = mark ? Bru.taskList.markTask(position) : Bru.taskList.unmarkTask(position);

        if (task == null) {
            System.out.println("Sorry, the task was not found.");
            return;
        }

        if (mark) {
            System.out.println(markedMsg);
        } else {
            System.out.println(unmarkedMsg);
        }
        System.out.println(task);
    }

    private static void addTask(Task task) {
        Bru.taskList.addTask(task);
        System.out.println(String.format("added: %s", task));
        System.out.println(String.format("Now you have %d tasks in the list.", Bru.taskList.size()));
    }

    private static void addTodoTask(String[] parms) {
        String msg = String.join(" ", parms);
        Task task = new TodoTask(msg);
        Bru.addTask(task);
    }

    private static void addDeadlineTask(String[] parms) {
        int delimiterPosition = Utils.findInArr(parms, "/by");
        String msg = String.join(" ",
                Arrays.copyOfRange(parms, 0,delimiterPosition));
        String deadline = String.join(" ",
                Arrays.copyOfRange(parms,delimiterPosition + 1,parms.length));
        Task task = new DeadlineTask(msg, deadline);
        Bru.addTask(task);
    }

    private static void addEventTask(String[] parms) {
        int startPosition = Utils.findInArr(parms, "/from");
        int endPosition = Utils.findInArr(parms, "/to");
        String msg = String.join(" ",
                Arrays.copyOfRange(parms, 0,startPosition));
        String start = String.join(" ",
                Arrays.copyOfRange(parms, startPosition + 1,endPosition));
        String end = String.join(" ",
                Arrays.copyOfRange(parms, endPosition + 1,parms.length));
        Task task = new EventTask(msg, start, end);
        Bru.addTask(task);
    }

    public static void main(String[] args) {
        Bru.displayWelcomeMsg();
        Scanner scanner = new Scanner(System.in);
        boolean isChatting = true;

        while (isChatting) {
            String input = scanner.nextLine();
            Pair<Command, String[]> pair = Bru.parseInput(input);
            Command command = pair.getFirst();
            String[] parms = pair.getSecond();

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
            default:
                Bru.displayUnknownMsg();
            }
        }

        Bru.displayGoodbyeMsg();
    }
}
