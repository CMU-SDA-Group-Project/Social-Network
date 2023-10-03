package com.hansy.accessingdataneo4j;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface PersonRepository extends Neo4jRepository<Person,Long> {

    Person findByName(String name);

    // to be noticed: the relationship Friend is defined in Person.java
    List<Person> findByFriendsName(String name);
}
