package banquemisr.challenge05.taskmanager.repository;

import banquemisr.challenge05.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByDueDate(LocalDate upcomingDeadline);
}
