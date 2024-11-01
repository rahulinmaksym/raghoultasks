package com.raghoul.raghoultasks.service.task;

import com.raghoul.raghoultasks.dto.task.TaskDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    ResponseEntity<List<TaskDto>> getAll();
    ResponseEntity<TaskDto> getById(UUID id);
    ResponseEntity<List<TaskDto>> getByOwnerId(UUID ownerId);
    ResponseEntity<TaskDto> save(TaskDto task);
}
