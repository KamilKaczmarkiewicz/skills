package com.project.skill.task;

import lombok.RequiredArgsConstructor;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class TaskCacheService {

    private final TaskRepository taskRepository;

    @CacheEvict(value = "tasks", key = "#taskId")
    public void updateProgressWithEviction(String taskId, Double progress) {
        taskRepository.updateProgress(taskId, progress);
    }
}