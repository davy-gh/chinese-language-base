package cz.magix.chibot.model;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

import cz.magix.chibot.constants.IndexName;
import cz.magix.chibot.constants.Level;

@NodeEntity
public class User {
	public static final String TRADITIONAL = "traditional";
	public static final String SIMPLIFIED = "simplified";
	public static final String PRONUNCIATION = "pronunciation";

	@GraphId
	private Long nodeId;
	
	@Indexed(indexName=IndexName.USER, unique=true)
	private char userName;

	private Level level;

	/*
	 * Constructors
	 */
	public User() {
	}
	
	public User(char userName) {
		super();
		this.userName = userName;
	}

	/*
	 * Getters & Setters
	 */
	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public char getUserName() {
		return userName;
	}

	public void setUserName(char userName) {
		this.userName = userName;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}
}
