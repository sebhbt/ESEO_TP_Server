package com.ESEO_Server_TWIC.blo;

import java.util.List;

import com.ESEO_Server_TWIC.dto.Ville;

public interface VilleBLO {
	
	public List<Ville> getInfoVilles();
	
	public Ville getInfoVille(String codeINSEE);

}
