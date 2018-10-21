package com.hacktx.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SentimentalData {
	@JsonProperty("keyPhrases")
	private List<String> keyPhrases;
	
	@JsonProperty("score")
	private Double score;
	
	@JsonProperty("original_text")
	private String original_text;
		
	public SentimentalData(List<String> keyPhrases, Double score, String original_text) {
		super();
		this.keyPhrases = keyPhrases;
		this.score = score;
		this.original_text = original_text;
	}
	
	public List<String> getKeyPhrases() {
		return keyPhrases;
	}
	public void setKeyPhrases(List<String> keyPhrases) {
		this.keyPhrases = keyPhrases;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public String getOriginal_text() {
		return original_text;
	}
	public void setOriginal_text(String original_text) {
		this.original_text = original_text;
	}

	@Override
	public String toString() {
		return "SentimentalDataRequest [keyPhrases=" + keyPhrases + ", score=" + score + ", original_text="
				+ original_text + "]";
	}

	
}
