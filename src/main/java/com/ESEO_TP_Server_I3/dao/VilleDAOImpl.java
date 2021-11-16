package com.ESEO_TP_Server_I3.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ESEO_TP_Server_I3.dto.Ville;

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
					// System.out.println("Ville: " + result.getString(2));
					Ville ville = new Ville();
					ville.setCodeCommune(result.getString(1));
					ville.setNomCommune(result.getString(2));
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
}
