package com.dao;

import java.util.List;

import com.dto.Ville;

public interface VilleDAO {
	
	public List<Ville> findVilles();
	
	public Ville findVille(String codeINSEE);

}
