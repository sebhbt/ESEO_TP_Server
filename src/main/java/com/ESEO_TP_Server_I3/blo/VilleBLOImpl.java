package com.ESEO_TP_Server_I3.blo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ESEO_TP_Server_I3.dao.VilleDAO;
import com.ESEO_TP_Server_I3.dto.Ville;

@Service
public class VilleBLOImpl implements VilleBLO{
	
	@Autowired
	private VilleDAO villeDAO;
	
	@Override
	public List<Ville> getInfoVilles() {
		//Code metier
		List<Ville> villes = villeDAO.findVilles();
		return villes;
	};

}
