package core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.*;


@NodeEntity(label = "person")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    @Property
    private String name;

    @Property
    private String encryptedPassword;

    @Property
    private Long credit=0L;

    @Property
    private String role;
}
