package com.controller;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.blo.DistanceBLO;
import com.dto.Coordonnees;

@RestController
public class DistanceController {

	@Autowired
	DistanceBLO distanceService;
	
	@RequestMapping(value = "/distance", method = RequestMethod.POST)
	@ResponseBody
	public double appelPost(@RequestBody String points) throws JSONException {
		JSONArray pointsJSON = new JSONArray(points);
		Coordonnees startPoint = new Coordonnees(pointsJSON.getJSONObject(0).getString("latitude"), pointsJSON.getJSONObject(0).getString("longitude"));
		Coordonnees endPoint = new Coordonnees(pointsJSON.getJSONObject(1).getString("latitude"), pointsJSON.getJSONObject(1).getString("longitude"));
		return distanceService.getDistance(startPoint, endPoint);
	}
}
