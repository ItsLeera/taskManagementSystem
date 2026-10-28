package com.aptech.taskManagementSystem.controller;

import com.aptech.taskManagementSystem.model.Task;
import com.aptech.taskManagementSystem.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3001")
@RestController("/tasks/api/v1")
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService =taskService;
    }

    @PostMapping("/tasks")
    public Task createTask(@RequestBody Task task){
        return taskService.createTask(task);
    }

    @GetMapping("/tasks")
    public List<Task> hello() {
        return taskService.listTask();
    }

    @DeleteMapping("task/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTaskById(id);
    }

    @PutMapping("task/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task){
        return taskService.updateTask(id, task);
    }

    @GetMapping("task/{id}")
    public Task getSingleTask(@PathVariable Long id){
        return taskService.getTaskById(id);
    }

}
