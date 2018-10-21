package com.hacktx.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "airport")
public class Airport {
	private String code;
	private String admiralsClubUrl;
	private String city;
	private String countryCode;
	private String countryName;
	private double latitude;
	private double longitude;
	private String name;
	private String stateCode;
	
	
	public Airport(String code, String admiralsClubUrl, String city, String countryCode, String countryName,
			double latitude, double longitude, String name, String stateCode) {
		super();
		this.code = code;
		this.admiralsClubUrl = admiralsClubUrl;
		this.city = city;
		this.countryCode = countryCode;
		this.countryName = countryName;
		this.latitude = latitude;
		this.longitude = longitude;
		this.name = name;
		this.stateCode = stateCode;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAdmiralsClubUrl() {
		return admiralsClubUrl;
	}
	public void setAdmiralsClubUrl(String admiralsClubUrl) {
		this.admiralsClubUrl = admiralsClubUrl;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

}
