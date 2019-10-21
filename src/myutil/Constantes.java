package myutil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class de Constantes
 * 
 * @author FERNANDO, MARIMOUTTOU, GHALMI
 * @version 1.1
 */
public class Constantes {

	public static String URL;
	public static String USER;
	public static String MDP;
	private static final String CONNEXION = "connexion.txt";
	public static final String SCRIPT = "script.txt";
	public static final String REQUETE_TOUS = "SELECT * from PROGRAMMEUR";
	public static final String REQUETE_UNIQUE = "SELECT * from PROGRAMMEUR WHERE matricule = ?";
	public static final String REQUETE_DELETE = "DELETE PROGRAMMEUR WHERE matricule = ?";
	public static final String REQUETE_INSERT = "INSERT INTO PROGRAMMEUR(ID,MATRICULE,NOM,PRENOM,ADRESSE,PSEUDO,RESPONSABLE,HOBBY,DATE_NAISS,DATE_EMB) VALUES(seq_programmeur.nextval,?,?,?,?,?,?,?,?,?)";
	public static final String REQUETE_UPDATE = "UPDATE PROGRAMMEUR SET NOM = ?, PRENOM = ?, ADRESSE = ?, PSEUDO = ?, RESPONSABLE = ?, HOBBY = ?, DATE_NAISS = ?, DATE_EMB = ? WHERE MATRICULE = ? ";

	
	public static void init() {
		try {
			FileReader file = new FileReader(new File(CONNEXION));
			BufferedReader buff = new BufferedReader(file);
			URL = buff.readLine();
			USER = buff.readLine();
			MDP = buff.readLine();
			buff.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
