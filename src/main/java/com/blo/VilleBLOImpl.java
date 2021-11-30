package com.blo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.VilleDAO;
import com.dto.Ville;

@Service
public class VilleBLOImpl implements VilleBLO{
	
	@Autowired
	private VilleDAO villeDAO;
	
	@Override
	public List<Ville> getInfoVilles() {
		return villeDAO.findVilles();
	}

	@Override
	public Ville getInfoVille(String codeINSEE) {
		return villeDAO.findVille(codeINSEE);
	}

	@Override
	public int postVille(Ville ville) {
		return villeDAO.createVille(ville);
	}

	@Override
	public int putVille(Ville ville) {
		return villeDAO.changeVille(ville);
	}

	@Override
	public int deleteVille(Ville ville) {
		return villeDAO.deleteVille(ville);
	}
}
