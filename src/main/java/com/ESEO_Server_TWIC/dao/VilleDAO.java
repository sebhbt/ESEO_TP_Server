package com.ESEO_Server_TWIC.dao;

import java.util.List;

import com.ESEO_Server_TWIC.dto.Ville;

public interface VilleDAO {
	
	public List<Ville> findVilles();
	
	public Ville findVille(String codeINSEE);

}
