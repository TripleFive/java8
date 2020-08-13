package com.stackroute.java8_exercise;

public class Country {
	
	private String countryName;
	private int countryId;
	
	
	public Country() {
		super();
	}
	
	public Country(String countryName, int countryId) {
		super();
		this.countryName = countryName;
		this.countryId = countryId;
	}
	
	public String getCountryName() {
		return countryName;
	}
	
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	public int getCountryId() {
		return countryId;
	}
	
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	@Override
	public String toString() {
		return "Country [countryName=" + countryName + ", countryId=" + countryId + "]";
	}
	
	
}
