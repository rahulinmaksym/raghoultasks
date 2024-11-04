package com.raghoul.raghoultasks.controller.task;

import com.raghoul.raghoultasks.service.task.TaskService;
import com.raghoul.raghoultasks.dto.task.TaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
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
        List<TaskDto> tasks = taskService.getAll();
        if(tasks.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(404))
                    .build();
        }
        return ResponseEntity.ok(tasks);
    }

    @GetMapping
    public ResponseEntity<TaskDto> getTaskById(@RequestParam UUID id) {
        TaskDto task = taskService.getById(id);
        if(task == null) {
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(404))
                    .build();
        }
        return ResponseEntity.ok(task);
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto task) {
        if(task == null) {
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(404))
                    .build();
        }
        return ResponseEntity.ok(taskService.save(task));
    }
}
