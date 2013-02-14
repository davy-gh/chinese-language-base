package cz.magix.chibot.model.relation;

import java.util.Date;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

import cz.magix.chibot.model.LanguageEntity;
import cz.magix.chibot.model.User;

@RelationshipEntity
public class Know {
	public final static String HAS_MEANING = "HAS_MEANING";
	
	@GraphId
	private Long relationshipId;
	
	@StartNode
	private User user;

	@EndNode
	private LanguageEntity languageEntity;
	
	private int failures;
	
	private int succeses;
	
	private Date learningStart;
	
	private Date learningLast;

	/*
	 * Getters & Setters
	 */
	public Long getRelationshipId() {
		return relationshipId;
	}

	public void setRelationshipId(Long relationshipId) {
		this.relationshipId = relationshipId;
	}

	public int getFailures() {
		return failures;
	}

	public void setFailures(int failures) {
		this.failures = failures;
	}

	public int getSucceses() {
		return succeses;
	}

	public void setSucceses(int succeses) {
		this.succeses = succeses;
	}

	public Date getLearningStart() {
		return learningStart;
	}

	public void setLearningStart(Date learningStart) {
		this.learningStart = learningStart;
	}

	public Date getLearningLast() {
		return learningLast;
	}

	public void setLearningLast(Date learningLast) {
		this.learningLast = learningLast;
	}	
}
