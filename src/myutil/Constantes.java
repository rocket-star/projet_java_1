/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myutil;

/**
 *
 * @author Jacques
 */
public class Constantes {

	public static final String OCCUPATION = "Programmeur";
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	public static final String USER = "SYSTEM";
	public static final String MDP = "rudy";
	public static final String REQUETE_TOUS = "SELECT * from PROGRAMMEUR";
	public static final String REQUETE_UNIQUE = "SELECT * from PROGRAMMEUR WHERE matricule = ?";
	public static final String REQUETE_DELETE = "DELETE PROGRAMMEUR WHERE matricule = ?";
	public static final String REQUETE_INSERT = "INSERT INTO PROGRAMMEUR(ID,MATRICULE,NOM,PRENOM,ADRESSE,PSEUDO,RESPONSABLE,HOBBY,DATE_NAISS,DATE_EMB) VALUES(seq_programmeur.nextval,?,?,?,?,?,?,?,TO_DATE(?, 'DD/MM/YYYY'),TO_DATE(?, 'DD/MM/YYYY'))";
	public static final String REQUETE_UPDATE = "UPDATE PROGRAMMEUR SET NOM = ?, PRENOM = ?, ADRESSE = ?, PSEUDO = ?, RESPONSABLE = ?, HOBBY = ?, DATE_NAISS = TO_DATE(?, 'DD/MM/YYYY'), DATE_EMB = TO_DATE(?, 'DD/MM/YYYY') WHERE MATRICULE = ? ";

}
