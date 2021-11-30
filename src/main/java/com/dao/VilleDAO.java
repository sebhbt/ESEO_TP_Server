package com.dao;

import java.util.List;

import com.dto.Ville;

public interface VilleDAO {
	
	public List<Ville> findVilles();
	
	public Ville findVille(String codeINSEE);
	
	public int createVille(Ville ville);

	public int changeVille(Ville ville);
}
