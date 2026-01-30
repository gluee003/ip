package bru;

import bru.command.Command;
import bru.command.CommandHandler;

import bru.exception.BruException;
import bru.exception.UnknownCommandException;

import bru.object.TaskList;

import bru.util.FileHandler;
import bru.util.Pair;
import bru.util.Parser;
import bru.util.Ui;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Scanner;

public class Bru {
    public static final String NAME = "Bru";
    private static final Path SAVE_FOLDER_PATH = Paths.get("data");
    private static final Path SAVE_FILE_PATH = Bru.SAVE_FOLDER_PATH.resolve("bru.txt");
    private static TaskList taskList;

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
                    CommandHandler.markTask(parms, true, Bru.taskList);
                    break;
                case UNMARK:
                    CommandHandler.markTask(parms, false, Bru.taskList);
                    break;
                case TODO:
                    CommandHandler.addTodoTask(parms, Bru.taskList);
                    break;
                case DEADLINE:
                    CommandHandler.addDeadlineTask(parms, Bru.taskList);
                    break;
                case EVENT:
                    CommandHandler.addEventTask(parms, Bru.taskList);
                    break;
                case DELETE:
                    CommandHandler.deleteTask(parms, Bru.taskList);
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
