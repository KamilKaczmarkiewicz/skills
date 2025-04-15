package com.project.skill.task;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document(collection = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class Task {

    @Id
    private String id;

    @Indexed
    private String personId;

    private List<ComparableObject> comparableObjects;

    /**
     * Represents the progress status as a percentage.
     * Expected format: Double value in the range 0.0 - 100.0,
     * where 75.0 means 75% complete.
     */
    private Double progressStatusPercentage;

    @CreatedDate
    private Instant createdAt;
}
