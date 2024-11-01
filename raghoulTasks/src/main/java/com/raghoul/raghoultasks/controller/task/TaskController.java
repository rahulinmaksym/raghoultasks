package com.raghoul.raghoultasks.controller.task;

import com.raghoul.raghoultasks.service.task.TaskService;
import com.raghoul.raghoultasks.dto.task.TaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/all")
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        return taskService.getAll();
    }

    @GetMapping
    public ResponseEntity<TaskDto> getTaskById(@RequestParam UUID id) {
        return taskService.getById(id);
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto task) {
        return taskService.save(task);
    }
}
