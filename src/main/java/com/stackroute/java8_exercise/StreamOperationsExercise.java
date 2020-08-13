package com.stackroute.java8_exercise;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamOperationsExercise {
	
	static List<Player> players = new ArrayList<>();
	
	static {
		players.add(new Player("Rohit", 224, 9115, 264, new Country("India", 101)));
		players.add(new Player("Smith", 125, 4162, 164, new Country("Australia", 102)));
		players.add(new Player("Root", 145, 5922, 133, new Country("England", 103)));
		players.add(new Player("Williamson", 151, 6174, 148, new Country("NewZealand", 104)));
		players.add(new Player("Virat", 248, 11867, 183, new Country("India", 101)));
		players.add(new Player("Amla", 181, 8113, 159, new Country("SouthAfrica", 101)));
		players.add(new Player("Irfan", 120, 1545, 83, new Country("India", 101)));
	}

	public static void main(String[] args) {
		
		System.out.println("display the names of all players using stream");
		displayPlayers();
		
		System.out.println("display the names of all players of a country with high score>100");
		displayPlayersForCountry("India");
		
		System.out.println("display players scored more than 5000 runs in descending order of names from a linked list");
		LinkedList<String> playerNameLinkedList = getPlayerNames();
		playerNameLinkedList.forEach((player)->{System.out.println(player);});
		
		System.out.println("display average runs scored from players of a country ");
		System.out.println(getAverageRunsByCountry("India"));
		
		System.out.println("display players in sorted country followed by descending order of matches played ");
		List<String> playerNameList = getPlayerNamesSorted();
		playerNameList.forEach((player)->{System.out.println(player);});
		
		System.out.println("using map display player name and country name for players played more than 200 matches");
		Map<String, String> playerNameCountryMap = getPlayerCountry();
		for (String playerName : playerNameCountryMap.keySet()) {
			System.out.println(playerName+ " : " + playerNameCountryMap.get(playerName));
		}
		
		System.out.println("display player who has scored maximum runs ");
		System.out.println(getMaxRunsPlayer());
		
		System.out.println("display player with a given name and country ");
		System.out.println(findPlayer("Root", "England"));
		
		System.out.println("checking whether any player in a country scored more than 10000 runs");
		System.out.println(checkHighScorerByCountry("India"));

	}
	
	static void displayPlayers() {
		Stream<Player> playerStream = players.stream();
		playerStream
		.forEach((player)->{System.out.println(player.getPlayerName());});
		}
	
	static void displayPlayersForCountry(String country) {
		Stream<Player> playerStream = players.stream();
		playerStream
		.filter((player)->{return (player.getHighestScore()>100) && (player.getCountry().getCountryName().equals(country));})
		.forEach((player)->{System.out.println(player.getPlayerName());});
		}
	
	static LinkedList<String> getPlayerNames() {
		Stream<Player> playerStream = players.stream();
		LinkedList<String> playerNameLinkedList = playerStream
		.filter((player)->{return player.getRuns()>5000;})
		.sorted((p1,p2)->p1.getPlayerName().compareTo(p2.getPlayerName())*-1)
		.map(Player::getPlayerName)
		.collect(Collectors.toCollection(LinkedList::new));
		
		return playerNameLinkedList;
		
		}
	
	static double getAverageRunsByCountry(String country) {
		Stream<Player> playerStream = players.stream();
		Double aveargeRuns =playerStream
		.filter((player)->{return player.getCountry().getCountryName().equals(country);})
		.collect(Collectors.averagingDouble((player)->{return player.getRuns();}));
		
		return aveargeRuns;
		
		}
	
	static List<String> getPlayerNamesSorted() {
		Stream<Player> playerStream = players.stream();
		List<String> playerNameList = playerStream
//		.sorted((p1,p2)->p1.getCountry().getCountryName().compareTo(p2.getCountry().getCountryName()))
		.sorted((p1,p2)->{
			if(p1.getCountry().getCountryName().compareTo(p2.getCountry().getCountryName())==0) {
				if(p1.getMatchesPlayed()>p2.getMatchesPlayed()) {return -1;}
			else if(p1.getMatchesPlayed()<p2.getMatchesPlayed()) {return 1;}
			return 0;}
			else {return p1.getCountry().getCountryName().compareTo(p2.getCountry().getCountryName());}
			})
		.map(Player::getPlayerName)
		.collect(Collectors.toList());
		
		return playerNameList;
		
		}
	
	static Map<String, String> getPlayerCountry() {
		Stream<Player> playerStream = players.stream();
		Map<String, String> playerNameCountryMap = playerStream
		.filter((player)->{return player.getMatchesPlayed()>200;})
		.collect(Collectors.toMap(player->player.getPlayerName(), player->player.getCountry().getCountryName()));
		
		return playerNameCountryMap;
		
		}
	
	static Player getMaxRunsPlayer() {
		Stream<Player> playerStream = players.stream();
		Player maxRunsPlayer = playerStream
		.collect(Collectors.maxBy((p1,p2)->p1.getRuns()-p2.getRuns()))
		.get();
		return maxRunsPlayer;
	}
	
	static Player findPlayer(String name, String country) {
		Stream<Player> playerStream = players.stream();
		Player playerToFind = playerStream
		.filter((player)->{return (player.getCountry().getCountryName().equals(country))&&(player.getPlayerName().equals(name));})
	    .findFirst()
	    .orElse(null);
		return playerToFind;
	}
	
	static boolean checkHighScorerByCountry(String country) {
		Stream<Player> playerStream = players.stream();
		boolean isHighScorerPresent = playerStream
		.anyMatch((player)->{return (player.getCountry().getCountryName().equals(country))&&(player.getRuns()>10000);});
		return isHighScorerPresent;
		
	}

}
