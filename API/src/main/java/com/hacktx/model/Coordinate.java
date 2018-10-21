package com.hacktx.model;

public class Coordinate {
	private int zipcode;
	private double latitude;
	private double longitude;
	private String city;
	private String state;
	private String county;

	public Coordinate(int zipcode, double latitude, double longitude, String city, String state, String county) {
		super();
		this.zipcode = zipcode;
		this.latitude = latitude;
		this.longitude = longitude;
		this.city = city;
		this.state = state;
		this.county = county;
	}

	public Coordinate() {
		this.zipcode = 0;
		this.latitude = 0;
		this.longitude = 0;
		this.city = "";
		this.state = "";
		this.county = "";
	}

	public int getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCounty() {
		return this.county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.city == null) ? 0 : this.city.hashCode());
		result = prime * result + ((this.county == null) ? 0 : this.county.hashCode());
		long temp;
		temp = Double.doubleToLongBits(this.latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(this.longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((this.state == null) ? 0 : this.state.hashCode());
		result = prime * result + this.zipcode;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (this.city == null) {
			if (other.city != null)
				return false;
		} else if (!this.city.equals(other.city))
			return false;
		if (this.county == null) {
			if (other.county != null)
				return false;
		} else if (!this.county.equals(other.county))
			return false;
		if (Double.doubleToLongBits(this.latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(this.longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		if (this.state == null) {
			if (other.state != null)
				return false;
		} else if (!this.state.equals(other.state))
			return false;
		if (this.zipcode != other.zipcode)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Coordinate [zipcode=" + this.zipcode + ", latitude=" + this.latitude + ", longitude=" + this.longitude + ", city="
				+ this.city + ", state=" + this.state + ", county=" + this.county + "]";
	}

}