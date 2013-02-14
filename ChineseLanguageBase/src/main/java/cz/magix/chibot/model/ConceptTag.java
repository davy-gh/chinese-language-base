package cz.magix.chibot.model;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

import cz.magix.chibot.constants.IndexName;

@NodeEntity
public class ConceptTag {
	public static final String TRADITIONAL = "traditional";
	public static final String SIMPLIFIED = "simplified";
	public static final String PRONUNCIATION = "pronunciation";

	@GraphId
	private Long nodeId;
	
	@Indexed(indexName=IndexName.CONCEPT_TAG)
	private char tagValue;

	/*
	 * Constructors
	 */
	public ConceptTag() {
	}

	/*
	 * Getters & Setters
	 */
	public char getTagValue() {
		return tagValue;
	}

	public void setTagValue(char tagValue) {
		this.tagValue = tagValue;
	}

}
