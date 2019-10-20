package data;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

/**
 * Interface ActionsBD
 * @author FERNANDO, MARIMOUTTOU, GHALMI
 * @version 1.1
 */

public interface ActionsBD {

	ResultSet getResultSet(String req);

	ArrayList getProgrammeurs();

	ProgrammeurBean getProgrammeur(String matricule) throws ProgrammeurInconnuExeption;

	void deleteProgrammeur(String matricule);

	void insertProgrammeur(String matricule, String nom, String prenom, String adresse, String pseudo,
			String responsable, String hobby, java.sql.Date dateDeNaissance, java.sql.Date dateEmbauche);

	void updateProgrammeur(String matricule, String nom, String prenom, String adresse, String pseudo,
			String responsable, String hobby, java.sql.Date dateDeNaissance, java.sql.Date dateEmbauche);
	
	String afficherProgrammeurs();
	
	void fermerRessources();
}
