package com.blo;

import java.util.List;

import com.dto.Ville;

public interface VilleBLO {
	
	public List<Ville> getInfoVilles();
	
	public Ville getInfoVille(String codeINSEE);

}