package com.project.skill.task;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
interface TaskRepository extends MongoRepository<Task, String> {

    Page<Task> findByPersonId(String personId, Pageable pageable);

    @Query("{ 'id' : ?0 }")
    @Update("{ '$set' : { 'progressStatusPercentage' : ?1 } }")
    void updateProgress(String taskId, Double progress);

    @Query("{ 'id': ?0, 'comparableObjects.id': ?1 }")
    @Update("{ '$set': { 'comparableObjects.$.classification': ?2, 'comparableObjects.$.similarityPercentage': ?3 } }")
    void updateComparableObjectInTask(String taskId, UUID comparableString, Classification classification, Double similarityPercentage);

}
