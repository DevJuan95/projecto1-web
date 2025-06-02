package unir.com.tasks.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unir.com.tasks.entity.Task;
import unir.com.tasks.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("api/task")
public class TaskController {
    private final TaskService taskService;

    TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(this.taskService.getAllTasks());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") Long id) {
        var task = this.taskService.getTaskById(id);
        return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Task>> getByUserId(@PathVariable("id") Long id) {
        var task = this.taskService.getTaskByUserId(id);
        return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return ResponseEntity.ok(this.taskService.createTask(task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") Long id, @RequestBody Task task) {
        return ResponseEntity.ok(this.taskService.updateTask(id,task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") Long id) {
        if(this.taskService.deleteTask(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
