package com.aptech.taskManagementSystem.service;

import com.aptech.taskManagementSystem.model.Task;

import java.util.List;

public interface TaskService {
    Task createTask(Task task);
    List<Task> listTask();
    Task getTaskById(Long id);
    Task updateTask(Long id, Task task);
    void deleteTaskById(Long id);

}
