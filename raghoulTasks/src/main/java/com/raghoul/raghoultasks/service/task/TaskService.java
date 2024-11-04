package com.raghoul.raghoultasks.service.task;

import com.raghoul.raghoultasks.dto.task.TaskDto;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    List<TaskDto> getAll();
    TaskDto getById(UUID id);
    List<TaskDto> getByOwnerId(UUID ownerId);
    TaskDto save(TaskDto task);
}
