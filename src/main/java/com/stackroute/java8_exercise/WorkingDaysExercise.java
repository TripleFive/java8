package com.stackroute.java8_exercise;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WorkingDaysExercise {

	public static void main(String[] args) {
		
		System.out.println("display working days of the next month from current date ");
		List<String> nextMonthWorkingDays = displayNextMonthsWorkingDays();
		nextMonthWorkingDays.forEach((workingDay)->{System.out.println(workingDay);});

	}
	
	static List<String> displayNextMonthsWorkingDays() {
		LocalDate currentDate = LocalDate.now();
		LocalDate nextMonthStartDate = currentDate.with(TemporalAdjusters.firstDayOfNextMonth());
		LocalDate nextMonthEndDate = nextMonthStartDate.with(TemporalAdjusters.lastDayOfMonth());
		long noOfDays = ChronoUnit.DAYS.between(nextMonthStartDate, nextMonthEndDate)+1;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		List<String> nextMonthWorkingDays=Stream.iterate(nextMonthStartDate, date->date.plusDays(1))
		.limit(noOfDays)
		.filter((date)->{return !(date.getDayOfWeek()==DayOfWeek.SATURDAY || date.getDayOfWeek()==DayOfWeek.SUNDAY);})
		.map(date->date.format(formatter))
		.collect(Collectors.toList());
		
		return nextMonthWorkingDays;
	}

}
