package core;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends Neo4jRepository<Friend,Long> {

    @Query("MATCH (startNode)-[relationship]->(endNode) " +
            "WHERE ID(startNode) = $startNodeId AND ID(endNode) = $endNodeId " +
            "RETURN relationship")
    Friend findRelationshipByNodeIds(@Param("startNodeId") Long startNodeId, @Param("endNodeId") Long endNodeId);

    @Query("MATCH (startNode)-[relationship]->(endNode) " +
            "WHERE ID(startNode) = $startNodeId " +
            "RETURN endNode")
    List<Person> getFriendByUserId(@Param("startNodeId") Long startNodeId);
}
