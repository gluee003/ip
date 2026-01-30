package bru.command;

import bru.exception.EmptyParmsException;
import bru.exception.InvalidParmsException;
import bru.exception.TaskNotFoundException;

import bru.object.TaskList;
import bru.object.TodoTask;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class CommandHandlerTest {
    @Test
    public void markTest_emptyParms() {
        String[] parms = new String[0];
        boolean isMarked = false;
        TaskList taskList = new TaskList();
        assertThrowsExactly(EmptyParmsException.class,
                () -> CommandHandler.markTask(parms, isMarked, taskList));
    }

    @Test
    public void markTest_invalidParms() {
        String[] parms = new String[] {"6 7"};
        boolean isMarked = false;
        TaskList taskList = new TaskList();
        assertThrowsExactly(InvalidParmsException.class,
                () -> CommandHandler.markTask(parms, isMarked, taskList));
    }

    @Test
    public void markTest_emptyList() {
        String[] parms = new String[] {"1"};
        boolean isMarked = false;
        TaskList taskList = new TaskList();
        assertThrowsExactly(TaskNotFoundException.class,
                () -> CommandHandler.markTask(parms, isMarked, taskList));
    }

    @Test
    public void markTest_notFound() {
        String[] parms = new String[] {"2"};
        boolean isMarked = false;
        TaskList taskList = new TaskList();
        taskList.addTask(new TodoTask("6 7"));
        assertThrowsExactly(TaskNotFoundException.class,
                () -> CommandHandler.markTask(parms, isMarked, taskList));
    }

    @Test
    public void markTest_found() {
        String[] parms = new String[] {"1"};
        boolean isMarked = false;
        TaskList taskList = new TaskList();
        taskList.addTask(new TodoTask("6 7"));
        CommandHandler.markTask(parms, isMarked, taskList);
    }
}
