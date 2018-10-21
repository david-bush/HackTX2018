package com.hacktx.repository;

import java.util.List;

import com.hacktx.model.Sentiment;

public interface SentimentRepository {

	public void addNewSentiment(Sentiment sentiment);
	public void addNewSentiments(List<Sentiment> sentiments);
}
