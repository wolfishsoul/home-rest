package viktor.home.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import viktor.home.domain.Status;
import viktor.home.domain.Task;
import viktor.home.exceptions.ForbiddenActionException;
import viktor.home.exceptions.NotFoundException;
import viktor.home.repository.TaskRepository;

import java.util.Optional;

class TaskServiceTest {
    private TaskRepository taskRepository = mock(TaskRepository.class);
    private TaskService sut = new TaskService(taskRepository);

    @Test
    void create_task_should_create_task() {
        Task task = Task.builder().status(Status.UNSTARTED).name("Name").description("desc").build();
        when(taskRepository.save(task)).thenReturn(task);

        assertThat(sut.createTask(task)).isEqualTo(task);
        verify(taskRepository).save(task);

    }

    @Test
    void create_task_should_not_create_task_if_status_is_different_than_unstarted() {
        Task task = Task.builder().status(Status.DOING).name("Name").description("desc").build();

        verify(taskRepository, never()).save(any());
        assertThat(sut.createTask(task)).isEqualTo(null);
    }

    @Test
    void edit_task_should_edit() throws NotFoundException, ForbiddenActionException {
        Task oldTask = Task.builder().id(1L).status(Status.DOING).name("Name").description("desc").build();
        Task newTask = Task.builder().id(1L).status(Status.DOING).name("New Name").description("new desc").build();
        when(taskRepository.findById(1L)).thenReturn(Optional.of(oldTask));
        when(taskRepository.save(oldTask)).thenReturn(oldTask);

        assertThat(sut.editTask(newTask, 1L)).isEqualTo(newTask);
        verify(taskRepository).save(oldTask);
    }

    @Test
    void edit_task_should_throw_not_found_exception() {
        Task newTask = Task.builder().id(1L).status(Status.DOING).name("New Name").description("new desc").build();
        doReturn(Optional.empty()).when(taskRepository).findById(1L);

        NotFoundException thrown = assertThrows(
                NotFoundException.class,
                () -> sut.editTask(newTask, 1L),
                "Task doesnt exist"
        );

        assertTrue(thrown.getMessage().equals("Task doesnt exist"));
        verify(taskRepository, never()).save(any());
    }

    @Test
    void delete_task_should_delete_task() throws NotFoundException {
        Task task = Task.builder().id(1L).build();
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        sut.deleteTask(1L);

        verify(taskRepository).delete(task);
    }

    @Test
    void delete_task_should_throw_not_found_exception() throws NotFoundException {
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(
                NotFoundException.class,
                () -> sut.deleteTask(1L),
                "Task doesnt exist"
        );

        assertTrue(thrown.getMessage().equals("Task doesnt exist"));
        verify(taskRepository, never()).delete(any());
    }

    @Test
    void not_edit_task_if_status_is_done_and_throw_exception() throws NotFoundException, ForbiddenActionException {
        Task oldTask = Task.builder().id(1L).status(Status.DONE).name("Name").description("desc").build();
        Task newTask = Task.builder().id(1L).status(Status.DOING).name("New Name").description("new desc").build();
        when(taskRepository.findById(1L)).thenReturn(Optional.of(oldTask));
        when(taskRepository.save(oldTask)).thenReturn(oldTask);

        ForbiddenActionException thrown = assertThrows(
                ForbiddenActionException.class,
                () -> sut.editTask(newTask, 1L)
        );

        assertTrue(thrown.getMessage().equals("Can't edit task of status DONE"));
        verify(taskRepository, never()).save(any());
    }
}