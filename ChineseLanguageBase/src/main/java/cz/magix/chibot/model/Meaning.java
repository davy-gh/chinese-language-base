package cz.magix.chibot.model;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

import cz.magix.chibot.constants.IndexName;

@NodeEntity
public class Meaning {
	public final static String MEANING = "meaning";
	
	@GraphId
	private Long nodeId;

	@Indexed(indexName=IndexName.MEANING, unique=true)
	private String meaning;

	/*
	 * Contructor
	 */
	public Meaning() {
	}
	
	public Meaning(String meaning) {
		super();
		this.meaning = meaning;
	}

	/*
	 * Getters & Setters
	 */
	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
}
