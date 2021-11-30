package com.ESEO_Server_TWIC.blo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ESEO_Server_TWIC.dao.VilleDAO;
import com.ESEO_Server_TWIC.dto.Ville;

@Service
public class VilleBLOImpl implements VilleBLO{
	
	@Autowired
	private VilleDAO villeDAO;
	
	@Override
	public List<Ville> getInfoVilles() {
		//Code metier
		List<Ville> villes = villeDAO.findVilles();
		return villes;
	}

	@Override
	public Ville getInfoVille(String codeINSEE) {
		Ville ville = villeDAO.findVille(codeINSEE);
		return ville ;
	};

}
