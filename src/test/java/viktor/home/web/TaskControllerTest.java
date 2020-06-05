package viktor.home.web;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import viktor.home.domain.Task;
import viktor.home.exceptions.ForbiddenActionException;
import viktor.home.exceptions.NotFoundException;
import viktor.home.service.TaskService;

class TaskControllerTest {
    TaskService taskService = mock(TaskService.class);
    TaskController sut = new TaskController(taskService);

    @Test
    void create_task_should_call_service() {
        Task task = Task.builder().name("task").build();

        sut.createTask(task);

        verify(taskService).createTask(task);
    }

    @Test
    void edit_task_should_call_service() throws NotFoundException, ForbiddenActionException {
        Long task_id = 1L;
        Task task = Task.builder().name("task").id(task_id).build();

        sut.editTask(task, task_id);

        verify(taskService).editTask(task, task_id);
    }

    @Test
    void delete_task_should_call_service() throws NotFoundException {
        sut.deleteTask(1L);

        verify(taskService).deleteTask(1L);
    }
}