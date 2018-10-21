package com.hacktx.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sentiments")
public class Sentiment {
	private String language;
	private List<String> keyPhrases;
	private double sentiment;
	private String originalString;
	
	public Sentiment(String language, List<String> keyPhrases, double sentiment, String originalString) {
		super();
		this.language = language;
		this.keyPhrases = keyPhrases;
		this.sentiment = sentiment;
		this.originalString = originalString;
	}
	
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public List<String> getKeyPhrases() {
		return keyPhrases;
	}
	public void setKeyPhrases(List<String> keyPhrases) {
		this.keyPhrases = keyPhrases;
	}
	public double getSentiment() {
		return sentiment;
	}
	public void setSentiment(double sentiment) {
		this.sentiment = sentiment;
	}
	public String getOriginalString() {
		return originalString;
	}
	public void setOriginalString(String originalString) {
		this.originalString = originalString;
	}
	
	
}
