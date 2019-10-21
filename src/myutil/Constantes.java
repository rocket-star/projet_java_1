package myutil;

/**
 * Class de Constantes 
 * @author FERNANDO, MARIMOUTTOU, GHALMI
 * @version 1.1
 */
public class Constantes {

	public static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	public static final String USER = "SYSTEM";
	public static final String MDP = "SYSTEM";
	public static final String REQUETE_TOUS = "SELECT * from PROGRAMMEUR";
	public static final String REQUETE_UNIQUE = "SELECT * from PROGRAMMEUR WHERE matricule = ?";
	public static final String REQUETE_DELETE = "DELETE PROGRAMMEUR WHERE matricule = ?";
	public static final String REQUETE_INSERT = "INSERT INTO PROGRAMMEUR(ID,MATRICULE,NOM,PRENOM,ADRESSE,PSEUDO,RESPONSABLE,HOBBY,DATE_NAISS,DATE_EMB) VALUES(seq_programmeur.nextval,?,?,?,?,?,?,?,?,?)";
	public static final String REQUETE_UPDATE = "UPDATE PROGRAMMEUR SET NOM = ?, PRENOM = ?, ADRESSE = ?, PSEUDO = ?, RESPONSABLE = ?, HOBBY = ?, DATE_NAISS = ?, DATE_EMB = ? WHERE MATRICULE = ? ";

}
