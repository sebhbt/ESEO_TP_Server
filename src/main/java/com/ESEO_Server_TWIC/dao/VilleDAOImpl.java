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

	private Connection conn = null;
	private PreparedStatement statement = null;
	private ResultSet result = null;

	private ResultSet executionRequeteSQL(String requete) {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/maven?user=sonar&password=sonar");
			statement = conn.prepareStatement(requete);
			result = statement.executeQuery();
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());

		}
		return result;
	}

	private void fermerConnections() {
		try {
			if (result != null) {
				result.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Ville> findVilles() {
		List<Ville> listVille = new ArrayList<Ville>();
		String requete = "SELECT * FROM `ville_france`";
		ResultSet resultatRequete = executionRequeteSQL(requete);

		if (resultatRequete != null) {
			try {
				while (resultatRequete.next()) {
					Ville ville = new Ville();
					ville.setCodeINSEECommune(result.getString(1));
					ville.setNomCommune(result.getString(2));
					ville.setCodePostalCommune(result.getString(3));
					ville.setLibelleAcheminementCommune(result.getString(4));
					ville.setLatitudeCommune(result.getString(6));
					ville.setLongitudeCommune(result.getString(7));
					listVille.add(ville);
				}
			} catch (SQLException ex) {
				System.out.println("SQLException: " + ex.getMessage());
				System.out.println("SQLState: " + ex.getSQLState());
				System.out.println("VendorError: " + ex.getErrorCode());
			}
		}
		fermerConnections();
		return listVille;
	}

	@Override
	public Ville findVille(String codeINSEE) {
		Ville ville = new Ville();
		String requete = "SELECT * FROM `ville_france` WHERE Code_commune_insee = '" + codeINSEE + "'";
		ResultSet resultatRequete = executionRequeteSQL(requete);

		if (resultatRequete != null) {
			try {
				while (resultatRequete.next()) {
					ville.setCodeINSEECommune(result.getString(1));
					ville.setNomCommune(result.getString(2));
					ville.setCodePostalCommune(result.getString(3));
					ville.setLibelleAcheminementCommune(result.getString(4));
					ville.setLatitudeCommune(result.getString(6));
					ville.setLongitudeCommune(result.getString(7));
				}
			} catch (SQLException ex) {
				System.out.println("SQLException: " + ex.getMessage());
				System.out.println("SQLState: " + ex.getSQLState());
				System.out.println("VendorError: " + ex.getErrorCode());
			}
		}
		fermerConnections();
		return ville;
	}
}
