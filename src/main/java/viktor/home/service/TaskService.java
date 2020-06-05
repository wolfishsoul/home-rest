package viktor.home.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import viktor.home.domain.Status;
import viktor.home.domain.Task;
import viktor.home.exceptions.ForbiddenActionException;
import viktor.home.exceptions.NotFoundException;
import viktor.home.repository.TaskRepository;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public Task createTask(Task task){

        if(task.getStatus() != Status.UNSTARTED){
            return null;
        }

        return taskRepository.save(task);
    }

    public Task editTask(Task task, Long taskId) throws NotFoundException, ForbiddenActionException {
       Task taskFromDb = taskRepository.findById(taskId).orElseThrow(() -> new NotFoundException("Task doesnt exist"));

       if(taskFromDb.getStatus().equals(Status.DONE)){
           throw new ForbiddenActionException("Can't edit task of status DONE");
       }

       taskFromDb.setDescription(task.getDescription());
       taskFromDb.setIsActive(task.getIsActive());
       taskFromDb.setName(task.getName());
       taskFromDb.setStatus(task.getStatus());

       return taskRepository.save(taskFromDb);
    }

    public void deleteTask(Long id) throws NotFoundException {
        Task taskToDelete = taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Task doesnt exist"));
        taskRepository.delete(taskToDelete);
    }
}
