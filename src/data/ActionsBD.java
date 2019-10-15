package data;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface ActionsBD {

	ResultSet getResultSet(String req);

	ArrayList getProgrammeurs();

	ProgrammeurBean getProgrammeur(String matricule) throws ProgrammeurInconnuExeption;

	void deleteProgrammeur(String matricule);

	void insertProgrammeur(String matricule, String nom, String prenom, String adresse, String pseudo,
			String responsable, String hobby, String dateDeNaissance, String dateEmbauche);

	void updateProgrammeur(String matricule, String nom, String prenom, String adresse, String pseudo,
			String responsable, String hobby, String dateDeNaissance, String dateEmbauche);
	
	String afficherProgrammeurs();
	
	void fermerRessources();
}
