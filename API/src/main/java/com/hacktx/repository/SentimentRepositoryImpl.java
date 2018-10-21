package com.hacktx.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.hacktx.model.Sentiment;

@Repository
public class SentimentRepositoryImpl implements SentimentRepository {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void addNewSentiment(Sentiment sentiment) {
		this.mongoTemplate.save(sentiment);
	}

	@Override
	public void addNewSentiments(List<Sentiment> sentiments) {
		// TODO Auto-generated method stub
		
	}

}
