package com.hacktx.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SentimentalDataRequest {
	@JsonProperty("results")
	List<SentimentalData> results;
	
	public SentimentalDataRequest() {
		
	}

	public SentimentalDataRequest(List<SentimentalData> results) {
		super();
		this.results = results;
	}

	public List<SentimentalData> getResults() {
		return results;
	}

	public void setResults(List<SentimentalData> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "SentimentalDataRequest [results=" + results + "]";
	}
	
}
