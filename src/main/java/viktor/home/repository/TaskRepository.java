package viktor.home.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import viktor.home.domain.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
