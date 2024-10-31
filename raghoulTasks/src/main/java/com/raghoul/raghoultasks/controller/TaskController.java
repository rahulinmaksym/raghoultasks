package com.raghoul.raghoultasks.controller;

import com.raghoul.raghoultasks.Service.TaskService;
import com.raghoul.raghoultasks.entity.Task;
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
    public ResponseEntity<List<Task>> getAllTasks() {
        return taskService.getAll();
    }

    @GetMapping
    public ResponseEntity<Task> getTaskById(@RequestParam UUID id) {
        return taskService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return taskService.save(task);
    }
}
