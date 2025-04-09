package com.project.skill.task;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID personId;

    @Enumerated(EnumType.STRING)
    private Classification classification;


    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "old_name")),
            @AttributeOverride(name = "surname", column = @Column(name = "old_surname")),
            @AttributeOverride(name = "company", column = @Column(name = "old_company")),
            @AttributeOverride(name = "birthDate", column = @Column(name = "old_birth_date"))
    })
    private ComparableObject oldValues;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "new_name")),
            @AttributeOverride(name = "surname", column = @Column(name = "new_surname")),
            @AttributeOverride(name = "company", column = @Column(name = "new_company")),
            @AttributeOverride(name = "birthDate", column = @Column(name = "new_birth_date"))
    })
    private ComparableObject newValues;

    /**
     * Represents the similarity between the old and new values as a percentage.
     * Expected format: Double value in the range 0.0 - 100.0,
     * where 85.5 means 85.5% similarity.
     */
    private Double similarityPercentage;

    /**
     * Represents the progress status as a percentage.
     * Expected format: Double value in the range 0.0 - 100.0,
     * where 75.0 means 75% complete.
     */
    private Double progressStatusPercentage;

    @CreationTimestamp
    private Instant createdAt;
}
