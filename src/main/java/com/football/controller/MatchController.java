package com.football.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.football.service.FootballMatchService;

@RestController
public class MatchController {
	
	@Autowired
	FootballMatchService service;
	
	@RequestMapping(value = "/match/{year}")
	public int getProductList(@PathVariable int year) throws Exception {
		return service.noOfDraws(year);
	}
	
	
}
