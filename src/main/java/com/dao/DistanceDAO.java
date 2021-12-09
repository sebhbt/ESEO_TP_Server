package com.dao;

import com.dto.Coordonnees;

public interface DistanceDAO {

	public double findDistance(Coordonnees startPoint, Coordonnees endPoint);
	
}
