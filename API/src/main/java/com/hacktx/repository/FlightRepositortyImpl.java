package com.hacktx.repository;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.hacktx.model.Airport;
import com.hacktx.model.Flight;

@Repository
public class FlightRepositortyImpl implements FlightRepository {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public Set<String> getAllFlightNumbers() {
		Query query = new Query();
		query.addCriteria(Criteria.where("flightNumber").exists(true));
		List<Flight> res = this.mongoTemplate.find(query, Flight.class);
		Set<String> numbers = new LinkedHashSet<>();
		for(Flight f: res) {
			numbers.add(f.getFlightNumber());
		}
		return numbers;
	}

	@Override
	public List<Flight> getAllFlights() {
		Query query = new Query();
		query.addCriteria(Criteria.where("flightNumber").exists(true));
		return this.mongoTemplate.find(query, Flight.class);
	}

}
