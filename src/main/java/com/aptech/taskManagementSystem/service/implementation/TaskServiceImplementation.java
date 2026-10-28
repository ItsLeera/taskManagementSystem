package com.aptech.taskManagementSystem.service.implementation;

import com.aptech.taskManagementSystem.model.Task;
import com.aptech.taskManagementSystem.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImplementation implements TaskService {

    private final List<Task> tasks = new ArrayList<>();
    private long nextId = 1;

    @Override
    public synchronized Task createTask(Task task) {
        Task task1 = new Task(nextId++, task.getTitle(), task.getDescription(), task.getStatus(), task.getPriority(),task.getDueDate(),task.getCreatedAt());
        tasks.add(task1);
        return task1;
    }

    @Override
    public synchronized List<Task> listTask() {
        return List.copyOf(tasks);
    }

    @Override
    public Task getTaskById(Long id) {
        for(Task task : tasks){
            if(task.getId() == id){
                return task;
            }
        }
        throw taskNotFound(id);
    }

    @Override
    public Task updateTask(Long id, Task task) {
        for(int index = 0 ; index < tasks.size(); index++){
            Task existingTask = tasks.get(index);

            if(existingTask.getId() == id){
                Task updated = new Task(existingTask.getId() , task.getTitle(), task.getDescription(), task.getStatus(), task.getPriority(), task.getDueDate(), task.getCreatedAt());
                tasks.set(index, updated);
                return updated;
            }
        }
        throw taskNotFound(id);
    }

    @Override
    public void deleteTaskById(Long id) {
        boolean deleted = tasks.removeIf(  task -> task.getId() == id);
        if(!deleted){
            throw taskNotFound(id);
        }
    }

    private ResponseStatusException taskNotFound(Long id){
        return new ResponseStatusException(HttpStatus.NOT_FOUND,"Task with id " + id + " " + "was not found");
    }
}
