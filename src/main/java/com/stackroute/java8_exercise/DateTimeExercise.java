package com.stackroute.java8_exercise;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DateTimeExercise {
	
	static List<Tablet> tablets = new ArrayList<>();
	
	static {
		tablets.add(new Tablet("Dolo", "Micro Labs Ltd.", LocalDate.of(2018, 9, 3), LocalDate.of(2020, 12, 3)));
		tablets.add(new Tablet("Cetrizine", "LGM Pharma", LocalDate.of(2020, 1, 22), LocalDate.of(2020, 6, 27)));
		tablets.add(new Tablet("Paracetamol", "LGM Pharma", LocalDate.of(2020, 2, 24), LocalDate.of(2020, 7, 2)));
		tablets.add(new Tablet("Paracin", "Torque Pharma", LocalDate.of(2019, 12, 4), LocalDate.of(2021, 12, 14)));
		tablets.add(new Tablet("Azithromycin", "Wellona Pharma", LocalDate.of(2019, 5, 26), LocalDate.of(2021, 5, 26)));
		tablets.add(new Tablet("Itraconazole", "Pacific India Pharma", LocalDate.of(2020, 2, 14), LocalDate.of(2020, 7, 14)));
		tablets.add(new Tablet("Anofer", "LGM Pharma", LocalDate.of(2020, 2, 4), LocalDate.of(2020, 8, 4)));
	}
	
	public static void main(String[] args) {
		
		System.out.println("display tablet names expiring within given months from today using list");
		List<String> tabletNameList = getExpiringTablets(10);
		tabletNameList.forEach((tabletName)->{System.out.println(tabletName);});
		
		System.out.println("display tablet objects in the ascending order of expiry date using list");
		List<Tablet> tabletList = getExpiringTabletsSorted();
		tabletList.forEach((tablet)->{System.out.println(tablet);});
		
		System.out.println("using map display tablet name and duration between manufacture and expiry date");
		Map<String, String> tabletNamevalidityMap = getTabletExpiryPeriod();
		for (String tabletName : tabletNamevalidityMap.keySet()) {
			System.out.println(tabletName+ " : " + tabletNamevalidityMap.get(tabletName));
		}
		
		System.out.println("using map display manufacture name and list of their tablets expired in the same year");
		Map<String, List<String>> tabletManufactureSameyearExpiryMap = getSameYearExpiry();
		for (String manufacturerName : tabletManufactureSameyearExpiryMap.keySet()) {
			System.out.println(manufacturerName+ " : " + tabletManufactureSameyearExpiryMap.get(manufacturerName));
		}
		
	}
	
	static List<String> getExpiringTablets(Integer months) {
		LocalDate currentDate= LocalDate.now();
		Stream<Tablet> tabletStream = tablets.stream();
		List<String> tabletNameList = tabletStream
		.filter((tablet)->{if(currentDate.compareTo(tablet.getExpiryDate())<0&& currentDate.plusMonths(months).compareTo(tablet.getExpiryDate())>0)return true;return false;})
		.map(Tablet::getTabletName)
		.collect(Collectors.toList());
		
		return tabletNameList;
		
		}
	
	static List<Tablet> getExpiringTabletsSorted() {
		Stream<Tablet> tabletStream = tablets.stream();
		List<Tablet> tabletList = tabletStream
		.sorted((tab1,tab2)->tab1.getExpiryDate().compareTo(tab2.getExpiryDate()))
//		.map(Tablet::getTabletName)
		.collect(Collectors.toList());
		
		return tabletList;
		
		}
	
	static Map<String, String> getTabletExpiryPeriod() {
		Stream<Tablet> tabletStream = tablets.stream();
		Map<String, String> tabletNamevalidityMap = tabletStream
		.collect(Collectors.toMap(tab->tab.getTabletName(), tab->
		{String validityDuration = "";
		Period duration = Period.between(tab.getManufactureDate(), tab.getExpiryDate());
		if (duration.getYears()>0)validityDuration+=duration.getYears()+" years ";
		if (duration.getMonths()>0)validityDuration+=duration.getMonths()+" months ";
		if (duration.getDays()>0)validityDuration+=duration.getDays()+" days";
		return validityDuration;}));
		return tabletNamevalidityMap;
		
		}
	
	static Map<String, List<String>> getSameYearExpiry() {
		LocalDate currentDate = LocalDate.now();
		Stream<Tablet> tabletStream = tablets.stream();
		Map<String, List<String>> tabletManufactureSameyearExpiryMap = tabletStream
		.filter((tablet)->{if(currentDate.compareTo(tablet.getExpiryDate())>0&& currentDate.getYear()==tablet.getExpiryDate().getYear())return true;return false;})
//		.collect(Collectors.groupingBy(Tablet::getManufacturer, Collectors.mapping(Tablet::getTabletName, Collectors.toList())));
		.collect(Collectors.toMap(tablet->tablet.getManufacturer(), 
		tablet-> new ArrayList<>(Arrays.asList(tablet.getTabletName())),
		(o,n)->{o.addAll(n); return o;}));
		return tabletManufactureSameyearExpiryMap;
		
		}

}
