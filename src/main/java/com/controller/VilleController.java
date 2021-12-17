package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.blo.VilleBLO;
import com.dto.Ville;

@RestController
public class VilleController {

	@Autowired
	VilleBLO villeService;

	@RequestMapping(value = "/ville", method = RequestMethod.GET)
	@ResponseBody
	public List<Ville> appelGet(@RequestParam(required = false, value = "codeINSEE") String codeINSEE) {
		List<Ville> villes = new ArrayList<Ville>();
		if (codeINSEE == null) {
			return villeService.getInfoVilles();
		} else {
			villes.add(villeService.getInfoVille(codeINSEE));
			return villes;
		}
	}

	@RequestMapping(value = "/ville", method = RequestMethod.POST)
	@ResponseBody
	public String appelPost(@RequestBody String ville) throws JSONException {
		if (ville != null) {
			if (villeService.postVille(ville) != 0) {
				return ville;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	@RequestMapping(value = "/ville", method = RequestMethod.PUT)
	@ResponseBody
	public String appelPut(@RequestBody String ville) throws JSONException {
		if (ville != null) {
			if (villeService.putVille(ville) != 0) {
				return ville;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	@RequestMapping(value = "/ville", method = RequestMethod.DELETE)
	@ResponseBody
	public String appelDelete(@RequestParam(required = true, value = "codeINSEE") String codeINSEE) {
		if (codeINSEE != null) {
			if (villeService.deleteVille(codeINSEE) != 0) {
				return null;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
}
