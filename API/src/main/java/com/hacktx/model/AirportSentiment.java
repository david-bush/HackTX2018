package com.hacktx.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "airportSentiment")
public class AirportSentiment extends Sentiment {
	private String airportCode;

	public AirportSentiment(String language, List<String> keyPhrases, double sentiment, String originalString, String airportCode) {
		super(language, keyPhrases, sentiment, originalString);
		this.airportCode = airportCode;
	}

	public String getAirportCode() {
		return airportCode;
	}

	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}

	@Override
	public String toString() {
		return "AirportSentiment [airportCode=" + airportCode + "]";
	}

}
