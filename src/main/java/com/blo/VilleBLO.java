package com.blo;

import java.util.List;

import org.codehaus.jettison.json.JSONException;

import com.dto.Ville;

public interface VilleBLO {
	
	public List<Ville> getInfoVilles();
	
	public Ville getInfoVille(String codeINSEE);
	
	public int postVille(String ville) throws JSONException;

	public int putVille(String ville) throws JSONException;

	public int deleteVille(String codeINSEE);
}
