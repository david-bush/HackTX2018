package com.hacktx.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.hacktx.model.User;

@Repository
public class UserDataRepositoryImpl implements UserDataRepository {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<User> findAllUsers() {
		Query query = new Query();
		query.addCriteria(Criteria.where("aadvantageId").exists(true));
		return this.mongoTemplate.find(query, User.class);
	}

}
