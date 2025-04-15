package com.project.skill.person;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
interface PersonRepository extends MongoRepository<Person, String> {
}
