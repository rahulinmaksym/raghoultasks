package com.raghoul.raghoultasks.mapper.task;

import com.raghoul.raghoultasks.dto.task.TaskDto;
import com.raghoul.raghoultasks.entity.task.Task;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface TaskMapper {

    TaskDto taskToTaskDto(Task entity);

    Task taskDtoToTask(TaskDto dto);

    List<TaskDto> taskListToTaskDtoList(List<Task> entityList);

    List<Task> taskDtoListToTaskList(List<TaskDto> dtoList);
}
