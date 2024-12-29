package br.ce.wcaquino.taskbackend.controller;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;


public class TaskControllerTest {

    @Mock
    private TaskRepo taskRepo;

    @InjectMocks
    private TaskController taskController;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldNotSaveTaskWithOutDescription(){
        Task todo = new Task();
        todo.setDueDate(LocalDate.now());

        ValidationException validationException = assertThrows(ValidationException.class, () ->
                taskController.save(todo));

        assertEquals("Fill the task description", validationException.getMessage());

    }

    @Test
    public void shouldNotSaveTaskWithOutDate(){
        Task todo = new Task();
        todo.setTask("Teste");

        ValidationException validationException = assertThrows(ValidationException.class, () ->
                taskController.save(todo));

        assertEquals("Fill the due date", validationException.getMessage());

    }

    @Test
    public void shouldNotSaveTaskWithPastDate(){
        Task todo = new Task();
        todo.setDueDate(LocalDate.now().minusYears(10));
        todo.setTask("Teste");

        ValidationException validationException = assertThrows(ValidationException.class, () ->
                taskController.save(todo));

        assertEquals("Due date must not be in past", validationException.getMessage());

    }

    @Test
    public void shouldSaveTaskWithSuccess() throws ValidationException {
        Task todo = new Task();
        todo.setDueDate(LocalDate.now());
        todo.setTask("Teste");

        taskController.save(todo);

        Mockito.verify(taskRepo).save(todo);

    }
}