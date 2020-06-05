package viktor.home.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import viktor.home.domain.Task;
import viktor.home.exceptions.ForbiddenActionException;
import viktor.home.exceptions.NotFoundException;
import viktor.home.service.TaskService;

@RestController
@RequestMapping(value = "tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Task createTask(@RequestBody Task task){
        return taskService.createTask(task);
    }

    @PutMapping(value = "{taskId}/edit")
    @ResponseStatus(HttpStatus.OK)
    Task editTask(@RequestBody Task task, @PathVariable Long taskId) throws NotFoundException, ForbiddenActionException {
        return taskService.editTask(task, taskId);
    }

    @DeleteMapping(value = "{taskId}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteTask(@PathVariable Long taskId) throws NotFoundException {
        taskService.deleteTask(taskId);
    }
}