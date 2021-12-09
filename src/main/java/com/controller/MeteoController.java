package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.blo.MeteoBLO;

@RestController
public class MeteoController {
	
	@Autowired
	MeteoBLO meteoService;

}
