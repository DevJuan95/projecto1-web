package unir.com.tasks.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import unir.com.tasks.entity.Task;
import unir.com.tasks.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    };

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    };

    public Optional<Task> getTaskById(Long id){
        return taskRepository.findById(id);
    }

    public Optional<List<Task>> getTaskByUserId(Long userId) {
        return taskRepository.findByUserId(userId);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task task) {
        var taskToUpdate = taskRepository.findById(id);
        if(taskToUpdate.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "");
        }
        taskToUpdate.get().updateFrom(task);
        return taskRepository.save(taskToUpdate.get());
    }

    public Boolean deleteTask(Long id) {
        var taskToDelete = taskRepository.findById(id);
        if(taskToDelete.isEmpty()) {
            return false;
        }
        taskRepository.deleteById(id);
        return true;
    }
}
