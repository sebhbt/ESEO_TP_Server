package com.dao;

import java.util.List;

import org.codehaus.jettison.json.JSONException;

import com.dto.Ville;

public interface VilleDAO {
	
	public List<Ville> findVilles();
	
	public Ville findVille(String codeINSEE);
	
	public int createVille(String ville) throws JSONException;

	public int changeVille(String ville) throws JSONException;
	
	public int deleteVille(String codeINSEE);
}
