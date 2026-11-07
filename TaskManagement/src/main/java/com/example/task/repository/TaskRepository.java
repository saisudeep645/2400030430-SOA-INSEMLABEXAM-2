
package com.example.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.task.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
