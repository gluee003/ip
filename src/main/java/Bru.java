import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Bru {
    public static final String NAME = "Bru";

    public static void main(String[] args) {
        System.out.println("Hello! I'm " + Bru.NAME);
        System.out.println("What can I do for you?\n");

        Scanner scanner = new Scanner(System.in);
        boolean isChatting = true;
        TaskList taskList = new TaskList();
        Pattern markPattern = Pattern.compile("mark [0-9]+");
        Pattern unmarkPattern = Pattern.compile("unmark [0-9]+");

        while (isChatting) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {                          // bye
                isChatting = false;
            } else if (input.equals("list")) {                  // list
                System.out.println(taskList);
            } else if (markPattern.matcher(input).matches()) {  // mark
                String value = input.split(" ")[1];
                int position = Integer.valueOf(value);
                Task task = taskList.markTask(position);
                if (task != null) {
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(task);
                } else {
                    System.out.println("Sorry, the task was not found.");
                }
            } else if (unmarkPattern.matcher(input).matches()) { // unmark
                String value = input.split(" ")[1];
                int position = Integer.valueOf(value);
                Task task = taskList.unmarkTask(position);
                if (task != null) {
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(task);
                } else {
                    System.out.println("Sorry, the task was not found.");
                }
            } else {                                            // add
                Task task = new Task(input);
                taskList.addTask(task);
                System.out.println(String.format("added: %s", task));
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
