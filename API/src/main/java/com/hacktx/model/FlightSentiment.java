package com.hacktx.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "flightSentiment")
public class FlightSentiment extends Sentiment {
	private String flightNumber;
	
	public FlightSentiment(String language, List<String> keyPhrases, double sentiment, String originalString, String flightNumber) {
		super(language, keyPhrases, sentiment, originalString);
		this.flightNumber = flightNumber;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	@Override
	public String toString() {
		return "FlightSentiment [flightNumber=" + flightNumber + ", language=" + language + ", keyPhrases=" + keyPhrases
				+ ", sentiment=" + sentiment + ", originalString=" + originalString + "]";
	}

}
