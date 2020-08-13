package com.stackroute.java8_exercise;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class BankDepositExercise {

	public static void main(String[] args) {
		
		Period investmentPeriod = Period.ofYears(2).plusMonths(6);
		System.out.println("Finding maturity date with investment startdate and duration");
		System.out.println(getMaturityDate("05-08-2020", investmentPeriod));
		
		System.out.println("Finding investment duration with investment startdate and maturity date");
		System.out.println(getInvestmentPeriod("05-08-2020", "05-02-2023"));

	}
	
	static String getMaturityDate(String investmentDate, Period duration) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		DateTimeFormatter outformatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
		LocalDate investmentStartDate = LocalDate.parse(investmentDate, formatter);
		LocalDate maturityDate = investmentStartDate.plus(duration);
		String formattedMaturityDate = maturityDate.format(outformatter);
		return formattedMaturityDate;
		
	}
	
	static String getInvestmentPeriod(String investmentDate, String maturityDate) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate investmentStartDate = LocalDate.parse(investmentDate, formatter);
		LocalDate investmentMaturityDate = LocalDate.parse(maturityDate, formatter);
		Period duration = Period.between(investmentStartDate, investmentMaturityDate);
//		System.out.println(duration);
		String investmentDuration = duration.getYears() + " years, " + duration.getMonths() + " months";
		return investmentDuration;
		
	}

}
