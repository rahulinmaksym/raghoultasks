package com.raghoul.raghoultasks.service.task;

import com.raghoul.raghoultasks.dto.task.TaskDto;
import com.raghoul.raghoultasks.entity.task.Task;
import com.raghoul.raghoultasks.mapper.task.TaskMapper;
import com.raghoul.raghoultasks.repository.task.TaskRepo;
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
    private final TaskMapper taskMapper;

    public ResponseEntity<List<TaskDto>> getAll() {
        List<TaskDto> tasks = taskMapper.taskListToTaskDtoList(taskRepo.findAll());
        if(tasks.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(404))
                    .build();
        }
        return ResponseEntity.ok(tasks);
    }

    public ResponseEntity<TaskDto> getById(UUID id) {
        TaskDto task = taskMapper.taskToTaskDto(taskRepo.findById(id).orElse(null));
        if(task == null) {
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(404))
                    .build();
        }
        return ResponseEntity.ok(task);
    }

    public ResponseEntity<List<TaskDto>> getByOwnerId(UUID ownerId) {
        List<TaskDto> tasks = taskMapper.taskListToTaskDtoList(taskRepo.findByOwnerId(ownerId));
        if(tasks.isEmpty()) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
        return ResponseEntity.ok(tasks);
    }

    public ResponseEntity<TaskDto> save(TaskDto task) {

        Task entity = taskMapper.taskDtoToTask(task);

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

        return ResponseEntity.ok(taskMapper.taskToTaskDto(taskRepo.save(entity)));
    }
}
