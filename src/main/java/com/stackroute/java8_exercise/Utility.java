package com.stackroute.java8_exercise;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utility {

	public static void main(String[] args) {
		
		Duration frequency = Duration.ofHours(1).plusMinutes(15);
		System.out.println("display working days of the next month from current date ");
		List<String> scheduleTiming = displayNextMonthsWorkingDays("06:30", frequency);
		scheduleTiming.forEach((scheduleTime)->{System.out.println(scheduleTime);});
	}
	
	static List<String> displayNextMonthsWorkingDays(String start, Duration frequency) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime startTime = LocalTime.parse(start,formatter);
		LocalTime endTime = LocalTime.parse("23:59",formatter);
		long totalDurationMinutes = ChronoUnit.MINUTES.between(startTime, endTime);
		long frequencyMinutes = frequency.toMinutes();
		List<String> scheduleTiming=Stream.iterate(startTime, time->time.plusMinutes(frequencyMinutes))
		.limit((totalDurationMinutes/frequencyMinutes)+1)
		.map(date->date.format(formatter))
		.collect(Collectors.toList());
		
		return scheduleTiming;
	}

}
