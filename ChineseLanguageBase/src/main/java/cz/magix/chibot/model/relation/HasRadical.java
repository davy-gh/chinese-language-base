package cz.magix.chibot.model.relation;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

import cz.magix.chibot.model.Char;
import cz.magix.chibot.model.Radical;

@RelationshipEntity
public class HasRadical {
	public final static String HAS_RADICAL = "HAS_RADICAL";

	@StartNode
	private Char character;

	@EndNode
	private Radical radical;
}
