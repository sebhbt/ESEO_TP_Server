package com.dao;

import org.springframework.stereotype.Repository;

import com.dto.Coordonnees;

@Repository	
public class DistanceDAOImpl implements DistanceDAO{

	@Override
	public double findDistance(Coordonnees startPoint, Coordonnees endPoint) {
		double d2r = Math.PI / 180;
		double distance = 0;
		double dlong = (Double.parseDouble(endPoint.getLongitude()) - Double.parseDouble(startPoint.getLongitude()))
				* d2r;
		double dlat = (Double.parseDouble(endPoint.getLatitude()) - Double.parseDouble(startPoint.getLatitude())) * d2r;
		double a = Math.pow(Math.sin(dlat / 2.0), 2)
		            + Math.cos(Double.parseDouble(startPoint.getLatitude()) * d2r)
		            * Math.cos(Double.parseDouble(endPoint.getLatitude()) * d2r)
		            * Math.pow(Math.sin(dlong / 2.0), 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    distance = 6367 * c;

	    return distance;
	}

}
