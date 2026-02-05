package bru.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import bru.exception.FileCorruptException;
import bru.object.DeadlineTask;
import bru.object.EventTask;
import bru.object.Task;
import bru.object.TaskList;
import bru.object.TodoTask;

/**
 * The FileHandler class handles reading and writing from a save file.
 */
public class FileHandler {
    private static Task fromRow(String[] row) {
        String taskType = row[0];
        boolean isDone = row[1].equals("1");
        String message = row[2];

        switch (taskType) {
        case "T":
            return new TodoTask(message, isDone);
        case "D":
            LocalDate deadline = LocalDate.parse(row[3]);
            return new DeadlineTask(message, isDone, deadline);
        case "E":
            LocalDate start = LocalDate.parse(row[3]);
            LocalDate end = LocalDate.parse(row[4]);
            return new EventTask(message, isDone, start, end);
        default:
            throw new FileCorruptException("");
        }
    }

    /**
     * Writes the information in the task list to a file.
     *
     * @param path     The relative path to the file
     * @param taskList The task list to be written
     */
    public static void writeToFile(Path path, TaskList taskList) {
        try {
            FileWriter fileWriter = new FileWriter(path.toFile());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (String row : taskList.toRows()) {
                bufferedWriter.write(row);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the information from a file and converts it into a task list.
     *
     * @param path The relative path to the file
     * @return The TaskList object containing tasks from the file
     */
    public static TaskList readFromFile(Path path) {
        try {
            FileReader fileReader = new FileReader(path.toFile());
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            List<String[]> data = bufferedReader.lines()
                    .map(row -> row.split("\\|", -1))
                    .toList();
            bufferedReader.close();
            TaskList taskList = new TaskList();

            for (String[] row : data) {
                Task task = FileHandler.fromRow(row);
                taskList.addTask(task);
            }

            return taskList;
        } catch (Exception e) {
            return new TaskList();
        }
    }

    /**
     * Creates a folder at the path if it does not exist.
     *
     * @param path The relative path to the folder
     */
    public static void createFolder(Path path) {
        if (Files.exists(path)) {
            return;
        }
        path.toFile().mkdir();
    }

    /**
     * Creates a file at the path if it does not exist.
     *
     * @param path The relative path to the file
     */
    public static void createFile(Path path) {
        if (Files.exists(path)) {
            return;
        }
        try {
            Files.createFile(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
