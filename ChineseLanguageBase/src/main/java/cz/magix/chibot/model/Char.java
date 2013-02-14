package cz.magix.chibot.model;

import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

import cz.magix.chibot.constants.IndexName;

@NodeEntity
public class Char extends LanguageEntity {
	public static final String TRADITIONAL = "traditional";
	public static final String SIMPLIFIED = "simplified";
	public static final String PRONUNCIATION = "pronunciation";

	@Indexed(indexName=IndexName.TRADITIONAL_CHARACTER)
	private char traditional;

	@Indexed(indexName=IndexName.SIMPLIFIED_CHARACTER, unique=true)
	private char simplified;

	@Indexed(indexName=IndexName.PRONUNCIATION)
	private String pronunciation;

	/*
	 * Constructors
	 */
	public Char() {
	}
	
	public Char(char traditional, char simplified, String pronunciation) {
		super();
		this.traditional = traditional;
		this.simplified = simplified;
		this.pronunciation = pronunciation;
	}

	/*
	 * Getters & Setters
	 */
	public char getTraditional() {
		return traditional;
	}

	public void setTraditional(char traditional) {
		this.traditional = traditional;
	}

	public char getSimplified() {
		return simplified;
	}

	public void setSimplified(char simplified) {
		this.simplified = simplified;
	}

	public String getPronunciation() {
		return pronunciation;
	}

	public void setPronunciation(String pronunciation) {
		this.pronunciation = pronunciation;
	}

	@Override
	public String toString() {
		return traditional + " " + simplified + " [" + pronunciation + "]";
	}
}
