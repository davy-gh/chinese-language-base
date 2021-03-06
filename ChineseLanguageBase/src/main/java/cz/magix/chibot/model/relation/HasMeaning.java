package cz.magix.chibot.model.relation;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

import cz.magix.chibot.model.Meaning;
import cz.magix.chibot.model.Word;

@RelationshipEntity
public class HasMeaning {
	public final static String HAS_MEANING = "HAS_MEANING";
	
	@GraphId
	private Long relationshipId;
	
	@StartNode
	private Word word;

	@EndNode
	private Meaning meaning;
}
