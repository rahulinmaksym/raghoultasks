package com.raghoul.raghoultasks.service.task;

import com.raghoul.raghoultasks.dto.task.TaskDto;
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

    public TaskDto save(TaskDto task) {
        return taskMapper.taskToTaskDto(taskRepo.save(taskMapper.taskDtoToTask(task)));
    }
}
