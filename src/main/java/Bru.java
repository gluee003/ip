import java.util.Scanner;

public class Bru {
    public static final String NAME = "Bru";

    public static void main(String[] args) {
        System.out.println("Hello! I'm " + Bru.NAME);
        System.out.println("What can I do for you?\n");

        Scanner scanner = new Scanner(System.in);
        boolean isChatting = true;
        TaskList taskList = new TaskList();
        while (isChatting) {
            String input = scanner.nextLine();
            switch (input) {
            case "bye":
                isChatting = false;
                break;
            case "list":
                System.out.println(taskList);
                break;
            default:
                Task task = new Task(input);
                taskList.addTask(task);
                System.out.println(String.format("added: %s", task));
                break;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
