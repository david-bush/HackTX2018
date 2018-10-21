package com.hacktx.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.hacktx.model.Airport;

@Repository
public class AirportRepositroyImpl implements AirportRepository {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<String> getAllAirportCodes() {
		Query query = new Query();
		query.addCriteria(Criteria.where("code").exists(true));
		List<Airport> res = this.mongoTemplate.find(query, Airport.class);
		List<String> codes = new ArrayList<>();
		for(Airport a: res) {
			codes.add(a.getCode());
		}
		return codes;
	}

	@Override
	public List<Airport> getAllAirports() {
		Query query = new Query();
		query.addCriteria(Criteria.where("code").exists(true));
		return this.mongoTemplate.find(query, Airport.class);
	}

}
