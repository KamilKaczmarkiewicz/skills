package com.project.skill.task;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
interface TaskRepository extends JpaRepository<Task, UUID> {

    @Query("SELECT t FROM Task t LEFT JOIN FETCH t.comparableObjects WHERE t.id = :id")
    Optional<Task> findByIdWithComparableObjects(UUID id);

    @Query(
            value = "SELECT DISTINCT t FROM Task t LEFT JOIN FETCH t.comparableObjects WHERE t.personId = :personId",
            countQuery = "SELECT COUNT(t) FROM Task t WHERE t.personId = :personId"
    )
    Page<Task> findByPersonIdWithComparableObjects(UUID personId, Pageable pageable);

    @Query(
            value = "SELECT DISTINCT t FROM Task t LEFT JOIN FETCH t.comparableObjects",
            countQuery = "SELECT COUNT(t) FROM Task t"
    )
    Page<Task> findAllWithComparableObjects(Pageable pageable);

    @Modifying
    @Transactional
    @Query("UPDATE Task t SET t.progressStatusPercentage = :progress WHERE t.id = :taskId")
    void updateProgress(UUID taskId, Double progress);
}
