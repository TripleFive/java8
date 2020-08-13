package com.stackroute.java8_exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LambdaExercise {
	
	static List<String> countries = new ArrayList<>();
	static Map<String, String> countryCapitals = new LinkedHashMap<String, String>();
	
	static {
		countries.add("Greece");
		countries.add("Australia");
		countries.add("India");
		countries.add("Canada");
		countries.add("Argentina");
		
		countryCapitals.put("Greece", "Athens");
		countryCapitals.put("Australia", "Canberra");
		countryCapitals.put("India", "New Delhi");
		countryCapitals.put("Canada", "Ottawa");
		countryCapitals.put("Argentina", "Buenos Aires");
	}

	public static void main(String[] args) {
		
		System.out.println("Displaying countries");
		displayCountries();
		
		System.out.println("Displaying countries and capitals");
		displayCountryCapitals();
		
		sortCountriesByName();
		System.out.println("Displaying countries in reverse alphabetical order");
		displayCountries();
		
		sortCountriesBylength();
		System.out.println("Displaying countries in descending length order");
		displayCountries();
		
		System.out.println("Deleting country names of length more than 6");
		//countries.forEach((country)->{removeCountry(country);});
		//for(String country:countries) {removeCountry(country);}
		for (int i=0; i<countries.size();i++) {
			if (removeCountry(countries.get(i))) {
				i--;
				
			}
		}
		displayCountries();

	}
	
	static void displayCountries() {
		countries.forEach((country)->{System.out.println(country);});
		
	}
	
	static void displayCountryCapitals() {
		countryCapitals.forEach((country, capital)->{System.out.println(country +" : "+capital);});
		
	}
	
	static void sortCountriesByName() {
		Collections.sort(countries,(c1,c2)->{
			return c1.compareTo(c2)*-1;
		});
		
	}
	
	static void sortCountriesBylength() {
		Collections.sort(countries,(c1,c2)->{
			if (c1.length()>c2.length()) {return -1;}
			else if(c1.length()<c2.length()) {return 1;}
			else {
				return c1.compareTo(c2);}
		});
		
	}
	
	static boolean removeCountry(String name) {
		
		if(name.length()>6) {
			countries.remove(name);
			return true;
		}
		return false;
	}

}
