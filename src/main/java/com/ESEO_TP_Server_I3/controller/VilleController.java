package com.ESEO_TP_Server_I3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ESEO_TP_Server_I3.blo.VilleBLO;
import com.ESEO_TP_Server_I3.dto.Ville;

@RestController
public class VilleController {
	
	@Autowired
	VilleBLO villeService;

	@RequestMapping(value = "/ville", method = RequestMethod.GET)
	@ResponseBody
	public List<Ville> appelGet(@RequestParam(required = false,value = "codePostal")String codePostal) {
		
		return villeService.getInfoVilles();
	}
}
