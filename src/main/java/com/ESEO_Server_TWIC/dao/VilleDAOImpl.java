package com.ESEO_Server_TWIC.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ESEO_Server_TWIC.dto.Ville;

@Repository
public class VilleDAOImpl implements VilleDAO {

	@Override
	public List<Ville> findVilles() {
		Connection conn = null;
		PreparedStatement statement;
		ResultSet result = null;
		List<Ville> listVille = new ArrayList<Ville>();
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/maven?user=sonar&password=sonar");
			statement = conn.prepareStatement("SELECT * FROM `ville_france`");
			result = statement.executeQuery();
			if (result != null) {
				while (result.next()) {
					Ville ville = new Ville();
					ville.setCodeINSEECommune(result.getString(1));
					ville.setNomCommune(result.getString(2));
					ville.setCodePostalCommune(result.getString(3));
					ville.setLatitudeCommune(result.getString(6));
					ville.setLongitudeCommune(result.getString(7));
					listVille.add(ville);
				}
			}

		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());

		}
		return listVille;
	}

	@Override
	public Ville findVille(String codeINSEE) {
		Connection conn = null;
		PreparedStatement statement;
		ResultSet result = null;
		Ville ville = new Ville();
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/maven?user=sonar&password=sonar");
			statement = conn.prepareStatement("SELECT * FROM `ville_france` WHERE Code_commune_insee = '" + codeINSEE +"'");
			result = statement.executeQuery();
			if (result != null) {
				while (result.next()) {
					ville.setCodeINSEECommune(result.getString(1));
					ville.setNomCommune(result.getString(2));
					ville.setCodePostalCommune(result.getString(3));
					ville.setLatitudeCommune(result.getString(6));
					ville.setLongitudeCommune(result.getString(7));
				}
			}

		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());

		}
		return ville;
	}
}
