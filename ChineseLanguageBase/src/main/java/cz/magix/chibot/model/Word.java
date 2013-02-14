package cz.magix.chibot.model;

import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

import cz.magix.chibot.constants.IndexName;

@NodeEntity
public class Word extends LanguageEntity{
	public final static String SIMPLIFIED = "simplified";
	public final static String TRADITIONAL = "traditional";

	@Indexed(indexName=IndexName.TRADITIONAL_WORD)
	private String traditional;
	
	@Indexed(indexName=IndexName.SIMPLIFIED_WORD, unique=true)
	private String simplified;

	/*
	 * Constructors
	 */
	public Word() {
	}
	
	public Word(String traditional, String simplified) {
		super();
		this.traditional = traditional;
		this.simplified = simplified;
	}

	/*
	 * Getters & Setters
	 */
	public String getTraditional() {
		return traditional;
	}

	public void setTraditionalWord(String traditional) {
		this.traditional = traditional;
	}

	public String getSimplified() {
		return simplified;
	}

	public void setSimplifiedWord(String simplified) {
		this.simplified = simplified;
	}
}
