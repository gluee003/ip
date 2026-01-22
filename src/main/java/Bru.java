import java.util.Scanner;
import java.util.regex.Pattern;

public class Bru {
    public static final String NAME = "Bru";
    private static final Pattern MARK_PATTERN = Pattern.compile("mark [0-9]+");
    private static final Pattern UNMARK_PATTERN = Pattern.compile("unmark [0-9]+");

    private static Command parseInput(String input) {
        if (input.equals("bye")) {
            return Command.BYE;
        } else if (input.equals("list")) {
            return Command.LIST;
        } else if (Bru.MARK_PATTERN.matcher(input).matches()) {
            return Command.MARK;
        } else if (Bru.UNMARK_PATTERN.matcher(input).matches()) {
            return Command.UNMARK;
        } else {
            return Command.ADD;
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm " + Bru.NAME);
        System.out.println("What can I do for you?\n");

        Scanner scanner = new Scanner(System.in);
        boolean isChatting = true;
        TaskList taskList = new TaskList();

        while (isChatting) {
            String input = scanner.nextLine();

            switch (Bru.parseInput(input)) {
            case BYE:
                isChatting = false;
                break;
            case LIST:
                System.out.println(taskList);
                break;
            case MARK: {
                String value = input.split(" ")[1];
                int position = Integer.valueOf(value);
                Task markedTask = taskList.markTask(position);
                if (markedTask != null) {
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(markedTask);
                } else {
                    System.out.println("Sorry, the task was not found.");
                }
                break;
            }
            case UNMARK: {
                String value = input.split(" ")[1];
                int position = Integer.valueOf(value);
                Task unmarkedTask = taskList.unmarkTask(position);
                if (unmarkedTask != null) {
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(unmarkedTask);
                } else {
                    System.out.println("Sorry, the task was not found.");
                }
                break;
            }
            case ADD:
                Task newTask = new Task(input);
                taskList.addTask(newTask);
                System.out.println(String.format("added: %s", newTask));
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
