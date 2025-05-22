package unir.com.tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unir.com.tasks.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findByUserId(Long userId);
}
