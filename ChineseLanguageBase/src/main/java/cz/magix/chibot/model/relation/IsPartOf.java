package cz.magix.chibot.model.relation;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

import cz.magix.chibot.model.Char;

@RelationshipEntity
public class IsPartOf {
	public static final String IS_PART_OF = "IS_PART_OF";

	@GraphId
	private Long relationshipId;

	@StartNode
	private Char contentCharacter;

	@EndNode
	private Char containsCharacter;
}
