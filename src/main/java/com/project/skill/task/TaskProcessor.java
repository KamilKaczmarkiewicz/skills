package com.project.skill.task;

import com.project.skill.task.dto.TaskDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
class TaskProcessor {

    private final TaskRepository taskRepository;
    private final ComparableObjectRepository comparableObjectRepository;

    @EventListener
    @Async
    void calculateTaskSimilarity(TaskDto taskDto) {
        log.info("Start calculateTaskSimilarity for taskId: {}", taskDto.id());
        var steps = 5;
        for (int i = 0; i < steps; i++) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            taskRepository.updateProgress(taskDto.id(), ((i + 1) * 100.0) / steps);
        }
        for (var comparable : taskDto.comparableObjects()) {
            var similarity = calculateSimilarity(comparable.previousValue(), comparable.newValue());
            var similarityPercentage = similarity * 100.0;
            var classification = getClassificationBySimilarity(similarity);
            comparableObjectRepository.updateClassificationAndSimilarity(comparable.id(), classification, similarityPercentage);
        }
    }

    Classification getClassificationBySimilarity(double similarity) {
        if (similarity > 0.9) {
            return Classification.HIGH;
        }
        if (similarity >= 0.4) {
            return Classification.MEDIUM;
        }
        return Classification.LOW;
    }


    double calculateSimilarity(String previousValue, String newValue) {
        if (previousValue == null) {
            previousValue = "";
        }
        if (newValue == null) {
            newValue = "";
        }
        var maxLength = Math.max(previousValue.length(), newValue.length());
        if (maxLength == 0) {
            return 1.0;
        }
        var distance = levenshteinDistance(previousValue, newValue);
        var dissimilarity = (double) distance / maxLength;
        return 1.0 - dissimilarity;
    }

    private int levenshteinDistance(String s1, String s2) {
        var n = s1.length();
        var m = s2.length();
        var dp = new int[n + 1][m + 1];
        for (var i = 0; i <= n; i++) {
            dp[i][0] = i;
        }
        for (var j = 0; j <= m; j++) {
            dp[0][j] = j;
        }
        for (var i = 1; i <= n; i++) {
            for (var j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(
                            dp[i - 1][j],
                            Math.min(dp[i][j - 1], dp[i - 1][j - 1])
                    );
                }
            }
        }
        return dp[n][m];
    }
}
