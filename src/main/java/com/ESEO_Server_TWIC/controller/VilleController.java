package com.ESEO_Server_TWIC.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ESEO_Server_TWIC.blo.VilleBLO;
import com.ESEO_Server_TWIC.dto.Ville;

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
	public ResponseEntity<String> appelPost(@RequestBody Ville ville) {
		if (ResponseEntity.status(HttpStatus.CREATED).build() != null) {
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
	}
}
