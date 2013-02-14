package cz.magix.chibot.model.relation;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

import cz.magix.chibot.model.Char;
import cz.magix.chibot.model.Word;

@RelationshipEntity
public class IsMemberOf {
	public static final String IS_MEMBER_OF = "IS_MEMBER_OF";
	
	@StartNode
	private Char character;
	
	@EndNode
	private Word word;
}
