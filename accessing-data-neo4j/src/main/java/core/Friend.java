package core;

import lombok.Builder;
import lombok.Data;
import org.neo4j.ogm.annotation.*;

@Data
@Builder
@RelationshipEntity(type = "FRIEND")
public class Friend {
    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    @Property
    private Person person;

    @EndNode
    @Property
    private Person friend;

}
