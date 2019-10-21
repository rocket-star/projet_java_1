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

	/**
	 * Lance la requete pass�e en paramètre et retourne le ResultSet
	 * correspondant à cette requête
	 *
	 * @param req La requête SQL que l'on souhaite ex�cuter
	 *            
	 * @return rs Une variable de type ResultSet
	 */
	ResultSet getResultSet(String req);
	
	/**
	 * R�cupère tout les programmeurs de la base de donn�es
	 *
	 * @return listeProgrammeurs : Une Liste de Programmeurs
	 */
	ArrayList getProgrammeurs();

	/**
	 * R�cupère toutes les infos d'un programmeur et retourne ce
	 * programmeur sous la forme d'un Java Bean Cette m�thode est utilis�e pour
	 * rechercher un progammeur via son matricule
	 *
	 * @param nom : Le nom saisi par l'utilisateur pour lancer la recherche
	 * @return prog : Une variable de type ProgrammeurBean
	 *
	 */
	ProgrammeurBean getProgrammeur(String matricule) throws ProgrammeurInconnuExeption;

	/**
	 * Supprime un programmeur correspondant au matricule pass� en param�tre
	 * 
	 * @param matricule : La matricule saisi par l'utilisateur utilis� pour lancer la recherche
	 *
	 */
	void deleteProgrammeur(String matricule);

	/**
	 * Ajoute un programmeur dans� la db
	 * 
	 * @param matricule : Le matricule du programmeur
	 * @param nom : Nom du programmeur
	 * @param prenom : Prenom du programmeur
	 * @param adresse : Adresse du programmeur
	 * @param pseudo : Pseudo du programmeur
	 * @param responsable : Responsable du programmeur
	 * @param hobby : Hobby du programmeur
	 * @param dateDeNaissance : Date de naissance du programmeur
	 * @param dateEmbauche : Date d'embauche du programmeur
	 *
	 */
	void insertProgrammeur(String matricule, String nom, String prenom, String adresse, String pseudo,
			String responsable, String hobby, java.sql.Date dateDeNaissance, java.sql.Date dateEmbauche);

	/**
	 * Permet de mettre �jour les donn�es d'un programmeur
	 * 
	 * @param matricule : Le (nouveau) matricule du programmeur
	 * @param nom : (nouveau) Nom du programmeur
	 * @param prenom : (nouveau) Prenom du programmeur
	 * @param adresse : (nouvelle) Adresse du programmeur
	 * @param pseudo : (nouveau) Pseudo du programmeur
	 * @param responsable : (nouveau) Responsable du programmeur
	 * @param hobby : (nouveau) Hobby du programmeur
	 * @param dateDeNaissance : (nouvelle) Date de naissance du programmeur
	 * @param dateEmbauche : (nouvelle) Date d'embauche du programmeur
	 *
	 */
	void updateProgrammeur(String matricule, String nom, String prenom, String adresse, String pseudo,
			String responsable, String hobby, java.sql.Date dateDeNaissance, java.sql.Date dateEmbauche);
	
	/**
	 * Permet de construire la cha�ne de caract�res qui sera
	 * affich� lorsqu'on choisit Programmeur - Afficher - Tous
	 *
	 * @return listeProg : cha�ne de caract�res de tout les programmeurs
	 *
	 */
	String afficherProgrammeurs();
	
	/**
	 * Permet de lib�rer les ressources li�es � la base de
	 * donn�es
	 */
	void fermerRessources();
}
