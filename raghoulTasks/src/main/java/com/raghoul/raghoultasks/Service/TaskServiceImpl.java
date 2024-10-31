package com.raghoul.raghoultasks.Service;

import com.raghoul.raghoultasks.entity.Task;
import com.raghoul.raghoultasks.repository.TaskRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepo taskRepo;

    public ResponseEntity<List<Task>> getAll() {
        List<Task> tasks = taskRepo.findAll();
        if(tasks.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(404))
                    .build();
        }
        return ResponseEntity.ok(tasks);
    }

    public ResponseEntity<Task> getById(UUID id) {
        Task task = taskRepo.findById(id).orElse(null);
        if(task == null) {
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(404))
                    .build();
        }
        return ResponseEntity.ok(task);
    }

    public ResponseEntity<List<Task>> getByOwnerId(UUID ownerId) {
        List<Task> tasks = taskRepo.findByOwnerId(ownerId);
        if(tasks.isEmpty()) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
        return ResponseEntity.ok(tasks);
    }

    public ResponseEntity<Task> save(Task task) {
        if(task == null) {
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(404))
                    .build();
        }

        UUID id = null;
        boolean isUnique = false;
        while(!isUnique) {
            id = UUID.randomUUID();
            isUnique = !taskRepo.existsById(id);
        }
        task.setId(id);

        UUID ownerId = null;
        isUnique = false;
        while(!isUnique) {
            ownerId = UUID.randomUUID();
            isUnique = !taskRepo.existsByOwnerId(ownerId);
        }
        task.setOwnerId(ownerId);

        return ResponseEntity.ok(taskRepo.save(task));
    }
}
