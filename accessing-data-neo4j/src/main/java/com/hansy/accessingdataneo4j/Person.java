package com.hansy.accessingdataneo4j;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@NodeEntity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    @Property
    private String name;

    @Property
    private Long credit;

    public Person(String name) {
        this.name = name;
        this.credit = 0L;
    }

    /**
     * Neo4j doesn't REALLY have bi-directional relationships. It just means when querying
     * * to ignore the direction of the relationship.
     * * https://dzone.com/articles/modelling-data-neo4j
     */
    // Important: the relationship should use @EqualsAndHashCode.Exclude,otherwise the lombok @Data will generate a stackoverflow error
    @EqualsAndHashCode.Exclude
    @Relationship(type = "FRIEND", direction = Relationship.UNDIRECTED)
    public Set<Person> friends;

    public void addFriend(Person person) {
        if (friends == null) {
            friends = new HashSet<>();
        }

        friends.add(person);
    }

    public void addCredit(Long credit) {
        this.credit += credit;
    }


    @Override
    public String toString() {
        return this.name + "'s friends => "
                + Optional.ofNullable(this.friends).orElse(
                Collections.emptySet()).stream()
                .map(Person::getName)
                .collect(Collectors.toList());
    }

}
