package com.raghoul.raghoultasks.service.task;

import com.raghoul.raghoultasks.dto.task.TaskDto;
import com.raghoul.raghoultasks.entity.task.Task;
import com.raghoul.raghoultasks.mapper.task.TaskMapper;
import com.raghoul.raghoultasks.repository.task.TaskRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepo taskRepo;
    private final TaskMapper taskMapper;

    public List<TaskDto> getAll() {
        return taskMapper.taskListToTaskDtoList(taskRepo.findAll());
    }

    public TaskDto getById(UUID id) {
        return taskMapper.taskToTaskDto(taskRepo.findById(id).orElse(null));
    }

    public List<TaskDto> getByOwnerId(UUID ownerId) {
        return taskMapper.taskListToTaskDtoList(taskRepo.findByOwnerId(ownerId));
    }

    public TaskDto save(TaskDto input) {

        Task task = taskMapper.taskDtoToTask(input);

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

        return taskMapper.taskToTaskDto(taskRepo.save(task));
    }
}
