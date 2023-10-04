package core;

import org.neo4j.ogm.annotation.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@NodeEntity(label = "person")
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
        return this.name + "'s teammates => "
                + Optional.ofNullable(this.friends).orElse(
                        Collections.emptySet()).stream()
                .map(Person::getName)
                .collect(Collectors.toList());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCredit() {
        return credit;
    }

    public void setCredit(Long credit) {
        this.credit = credit;
    }

}