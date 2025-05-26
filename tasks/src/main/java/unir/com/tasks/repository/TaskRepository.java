package unir.com.tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unir.com.tasks.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<List<Task>> findByUserId(Long userId);
}
