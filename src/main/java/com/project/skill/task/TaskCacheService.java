package com.project.skill.task;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
class TaskCacheService {

    private final TaskRepository taskRepository;

    @CacheEvict(value = "tasks", key = "#taskId")
    public void updateProgressWithEviction(UUID taskId, Double progress) {
        taskRepository.updateProgress(taskId, progress);
    }
}