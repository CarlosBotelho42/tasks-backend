package br.ce.wcaquino.taskbackend.controller;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TaskControllerTest {

    @Mock
    private TaskRepo todoRepo;

    @Test
    public void shouldNotSaveTaskWithOutDescription(){
        Task todo = new Task();
        todo.setDueDate(LocalDate.now());
        TaskController taskController = new TaskController();

        ValidationException validationException = assertThrows(ValidationException.class, () ->
                taskController.save(todo));

        assertEquals("Fill the task description", validationException.getMessage());

    }

    @Test
    public void shouldNotSaveTaskWithOutDate(){
        Task todo = new Task();
        //todo.setDueDate(LocalDate.now());
        todo.setTask("Teste");
        TaskController taskController = new TaskController();

        ValidationException validationException = assertThrows(ValidationException.class, () ->
                taskController.save(todo));

        assertEquals("Fill the due date", validationException.getMessage());

    }

    @Test
    public void shouldNotSaveTaskWithPastDate(){

    }

    @Test
    public void shouldSaveTaskWithSuccess(){

    }
}