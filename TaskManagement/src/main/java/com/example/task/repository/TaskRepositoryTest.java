
package com.example.task.repository;

import com.example.task.entity.Task;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(
    replace = AutoConfigureTestDatabase.Replace.NONE
)
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testSaveAndFindTask() {

        Task task = new Task(
            "Complete Assignment",
            "Finish Spring Boot experiment",
            false
        );

        // Save task
        Task savedTask = taskRepository.save(task);

        // Write changes to PostgreSQL
        taskRepository.flush();

        // Clear persistence context
        entityManager.clear();

        // Retrieve task using ID
        Task foundTask = taskRepository
                .findById(savedTask.getId())
                .orElse(null);

        // Verify results
        assertNotNull(savedTask.getId());
        assertNotNull(foundTask);

        assertEquals(
            "Complete Assignment",
            foundTask.getTitle()
        );

        System.out.println("Task saved successfully!");
        System.out.println("Task ID: " + foundTask.getId());
    }
}
