package com.raghoul.raghoultasks.Service;

import com.raghoul.raghoultasks.entity.Task;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    ResponseEntity<List<Task>> getAll();
    ResponseEntity<Task> getById(UUID id);
    ResponseEntity<List<Task>> getByOwnerId(UUID ownerId);
    ResponseEntity<Task> save(Task task);
}
