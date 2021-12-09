package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dto.Coordonnees;
import com.dto.Ville;

@Repository
public class VilleDAOImpl implements VilleDAO {

	private Connection conn = null;
	private PreparedStatement statement = null;
	private ResultSet result = null;

	private ResultSet executionGetSQL(String requete) {
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
	
	private int executionPostSQL(String requete) {
		int response = 0;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/maven?user=sonar&password=sonar");
			statement = conn.prepareStatement(requete);
			response = statement.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());

		}
		return response;
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
		List<Ville> listVille = new ArrayList<>();
		String requete = "SELECT * FROM `ville_france`";
		ResultSet resultatRequete = executionGetSQL(requete);
		if (resultatRequete != null) {
			try {
				while (resultatRequete.next()) {
					Ville ville = new Ville();
					Coordonnees coordonnees = new Coordonnees();
					ville.setCodeINSEECommune(result.getString(1));
					ville.setNomCommune(result.getString(2));
					ville.setCodePostalCommune(result.getString(3));
					ville.setLibelleAcheminementCommune(result.getString(4));
					coordonnees.setLatitude(result.getString(6));
					coordonnees.setLongitude(result.getString(7));
					ville.setCoordonnees(coordonnees);
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
		Coordonnees coordonnees = new Coordonnees();
		String requete = "SELECT * FROM `ville_france` WHERE Code_commune_insee = '" + codeINSEE + "'";
		ResultSet resultatRequete = executionGetSQL(requete);

		if (resultatRequete != null) {
			try {
				while (resultatRequete.next()) {
					ville.setCodeINSEECommune(result.getString(1));
					ville.setNomCommune(result.getString(2));
					ville.setCodePostalCommune(result.getString(3));
					ville.setLibelleAcheminementCommune(result.getString(4));
					coordonnees.setLatitude(result.getString(6));
					coordonnees.setLongitude(result.getString(7));
					ville.setCoordonnees(coordonnees);
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

	@Override
	public int createVille(Ville ville) {
		int response = 0;
		String requete = "INSERT INTO `ville_france`(`Code_commune_INSEE`, `Nom_commune`, `Code_postal`, `Libelle_acheminement`, `Ligne_5`, `Latitude`, `Longitude`) "
				+ "VALUES ('" + ville.getCodeINSEECommune() + "','" + ville.getNomCommune() + "','"
				+ ville.getCodePostalCommune() + "','" + ville.getLibelleAcheminementCommune() + "','','"
				+ ville.getCoordonnees().getLatitude() + "','" + ville.getCoordonnees().getLongitude() + "')";
		response = executionPostSQL(requete);
		fermerConnections();
		return response;
	}

	@Override
	public int changeVille(Ville ville) {
		int response = 0;
		String requete = "UPDATE `ville_france`"
				+ "SET `Code_commune_INSEE` = '" + ville.getCodeINSEECommune() 
				+ "', `Nom_commune` = '" + ville.getNomCommune() 
				+ "', `Code_postal` = '" + ville.getCodePostalCommune() 
				+ "', `Libelle_acheminement` = '" + ville.getLibelleAcheminementCommune() 
				+ "', `Ligne_5` = '"
				+ "', `Latitude` = '" + ville.getCoordonnees().getLatitude() 
				+ "', `Longitude` = '" + ville.getCoordonnees().getLongitude() + "' WHERE `Code_commune_INSEE` = '" + ville.getCodeINSEECommune() + "'";
		response = executionPostSQL(requete);
		fermerConnections();
		return response;
	}

	@Override
	public int deleteVille(Ville ville) {
		int response = 0;
		String requete = "DELETE FROM `ville_france` WHERE `Code_commune_INSEE` = '" + ville.getCodeINSEECommune() + "'";
		response = executionPostSQL(requete);
		fermerConnections();
		return response;
	}
	
	
}
