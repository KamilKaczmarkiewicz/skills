package com.project.skill.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
interface ComparableObjectRepository extends JpaRepository<ComparableObject, UUID> {

    @Modifying
    @Transactional
    @Query("UPDATE ComparableObject c SET c.classification = :classification, c.similarityPercentage = :similarityPercentage WHERE c.id = :comparableObjectId")
    void updateClassificationAndSimilarity(UUID comparableObjectId, Classification classification, Double similarityPercentage);
}
