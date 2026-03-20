package org.example.task;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {
    private final List<Task> tasks = new ArrayList<>();
    private final AtomicLong ids = new AtomicLong(0);

    public List<Task> findAll() {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getId))
                .toList();
    }

    public Task create(String title) {
        Task task = new Task(ids.incrementAndGet(), title, false);
        tasks.add(task);
        return task;
    }

    public Task toggleDone(Long id) {
        Task task = findById(id);
        task.setDone(!task.isDone());
        return task;
    }

    public void delete(Long id) {
        Task task = findById(id);
        tasks.remove(task);
    }

    private Task findById(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException(id));
    }
}
