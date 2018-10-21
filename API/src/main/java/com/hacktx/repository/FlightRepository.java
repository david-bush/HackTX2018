package com.hacktx.repository;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.hacktx.model.Flight;

@Repository
public interface FlightRepository {
	public Set<String> getAllFlightNumbers();
	public List<Flight> getAllFlights();
}
