package com.football.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FootballMatchService {
	
	@Autowired
	RestTemplate restTemplate;
	
	private final String BASE_URL = "https://jsonmock.hackerrank.com/api/football_matches";
	
	public int noOfDraws(int year) throws Exception{
		
		int maxGoal = 10;
		int draws = 0;
		
		int sleepMin = 3;
		int sleepMax = 6;
		
		int sleepFor = new Random().nextInt(sleepMax - sleepMin + 1) + sleepMin;
		
		for(int i = 0; i <= maxGoal; i++) {
			draws += getDrawsByGoal( BASE_URL + "?year=" + year, i );
		}
		
		try {
			Thread.sleep( sleepFor );
			return draws;
		} catch (Exception e) {
			System.out.println( "Error: Something went wrong!" );
		} 
		
		return -1;
				
	}
	
	private int getDrawsByGoal(String baseUrl, int goal) throws Exception{
		
		String apiurl = String.format( baseUrl + "&team1goals=" + goal + "&team2goals=" + goal);
		
		ResponseEntity<String> response = restTemplate.getForEntity(apiurl, String.class);
		
		String responseBody = response.getBody();
		
		JsonObject res = new Gson().fromJson(responseBody, JsonObject.class);
		
		return res.get("total").getAsInt();
	}

}
