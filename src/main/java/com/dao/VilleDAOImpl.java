package com.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Repository;

import com.dto.Coordonnees;
import com.dto.Ville;

@Repository
public class VilleDAOImpl implements VilleDAO {

	private Connection conn = null;
	private PreparedStatement statement = null;
	private ResultSet result = null;

	private Properties prop = new Properties();
	
	private static String SQLException = "SQLException: ";

	private ResultSet executionGetSQL(String requete) {
		try {
			prop.load(new FileInputStream("src/main/ressources/config.properties"));
			conn = DriverManager.getConnection(prop.getProperty("sql.url")+"?user="+prop.getProperty("sql.user")+"&password="+prop.getProperty("sql.password"));
			statement = conn.prepareStatement(requete);
			result = statement.executeQuery();
		} catch (SQLException | IOException ex) {
			System.out.println("SQLException: " + ex.getMessage());
		}
		return result;
	}
	
	private int executionPostSQL(String requete) {
		int response = 0;
		try {
			prop.load(new FileInputStream("src/main/ressources/config.properties"));
			conn = DriverManager.getConnection(prop.getProperty("sql.url")+"?user="+prop.getProperty("sql.user")+"&password="+prop.getProperty("sql.password"));
			statement = conn.prepareStatement(requete);
			response = statement.executeUpdate();
		} catch (SQLException | IOException ex) {
			System.out.println(SQLException + ex.getMessage());

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
			System.out.println(SQLException + e.getMessage());
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
				System.out.println(SQLException + ex.getMessage());
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
				System.out.println(SQLException + ex.getMessage());
			}
		}
		fermerConnections();
		return ville;
	}

	@Override
	public int createVille(String ville) throws JSONException {
		int response = 0;
		JSONObject jsonVille = new JSONObject(ville);
		Ville villeObj = new Ville(jsonVille.getString("codeINSEECommune"), 
				jsonVille.getString("nomCommune"), 
				jsonVille.getString("codePostalCommune"), 
				jsonVille.getString("libelleAcheminementCommune"), 
				new Coordonnees(jsonVille.getJSONObject("coordonnees").getString("latitude"), jsonVille.getJSONObject("coordonnees").getString("longitude")));
		String requete = "INSERT INTO `ville_france`(`Code_commune_INSEE`, `Nom_commune`, `Code_postal`, `Libelle_acheminement`, `Ligne_5`, `Latitude`, `Longitude`) "
				+ "VALUES ('" + villeObj.getCodeINSEECommune() + "','" + villeObj.getNomCommune() + "','"
				+ villeObj.getCodePostalCommune() + "','" + villeObj.getLibelleAcheminementCommune() + "','','"
				+ villeObj.getCoordonnees().getLatitude() + "','" + villeObj.getCoordonnees().getLongitude() + "')";
		response = executionPostSQL(requete);
		fermerConnections();
		return response;
	}

	@Override
	public int changeVille(String ville) throws JSONException {
		int response = 0;
		JSONObject jsonVille;
		jsonVille = new JSONObject(ville);
		Ville villeObj = new Ville(jsonVille.getString("codeINSEECommune"), 
				jsonVille.getString("nomCommune"), 
				jsonVille.getString("codePostalCommune"), 
				jsonVille.getString("libelleAcheminementCommune"), 
				null);
		String requete = "UPDATE `ville_france`"
				+ "SET `Code_commune_INSEE` = '" + villeObj.getCodeINSEECommune() 
				+ "', `Nom_commune` = '" + villeObj.getNomCommune() 
				+ "', `Code_postal` = '" + villeObj.getCodePostalCommune() 
				+ "', `Libelle_acheminement` = '" + villeObj.getLibelleAcheminementCommune() 
				+ "' WHERE `Code_commune_INSEE` = '" + villeObj.getCodeINSEECommune() + "'";
		response = executionPostSQL(requete);
		fermerConnections();
		return response;
	}

	@Override
	public int deleteVille(String codeINSEE) {
		int response = 0;
		String requete = "DELETE FROM `ville_france` WHERE `Code_commune_INSEE` = '" + codeINSEE + "'";
		response = executionPostSQL(requete);
		fermerConnections();
		return response;
	}
	
	
}
