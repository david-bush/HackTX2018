package com.hacktx.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hacktx.model.Airport;

@Repository
public interface AirportRepository {
	
	List<Airport> getAllAirports();
	List<String> getAllAirportCodes();
}
