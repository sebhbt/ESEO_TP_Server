package com.blo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.DistanceDAO;
import com.dto.Coordonnees;

@Service
public class DistanceBLOImpl implements DistanceBLO {

	@Autowired
	private DistanceDAO distanceDAO;

	@Override
	public double getDistance(Coordonnees startPoint, Coordonnees endPoint) {
		return distanceDAO.findDistance(startPoint, endPoint);
	}
}
