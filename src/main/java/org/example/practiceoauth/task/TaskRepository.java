package org.example.practiceoauth.task;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, String> {
    List<Task> findByUserEmail(String userEmail);
}
