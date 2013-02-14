package cz.magix.chibot.model;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public abstract class LanguageEntity {
	public static final String TRADITIONAL = "traditional";
	public static final String SIMPLIFIED = "simplified";
	public static final String PRONUNCIATION = "pronunciation";

	@GraphId
	private Long nodeId;

	/*
	 * Getters & Setters
	 */
	public Long getNodeId() {
		return nodeId;
	}
}
