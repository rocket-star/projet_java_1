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
	 * Lance la requete passée en paramÃ¨tre et retourne le ResultSet
	 * correspondant Ã  cette requÃªte
	 *
	 * @param req La requÃªte SQL que l'on souhaite exécuter
	 *            
	 * @return rs Une variable de type ResultSet
	 */
	ResultSet getResultSet(String req);
	
	/**
	 * RécupÃ¨re tout les programmeurs de la base de données
	 *
	 * @return listeProgrammeurs : Une Liste de Programmeurs
	 */
	ArrayList getProgrammeurs();

	/**
	 * RécupÃ¨re toutes les infos d'un programmeur et retourne ce
	 * programmeur sous la forme d'un Java Bean Cette méthode est utilisée pour
	 * rechercher un progammeur via son matricule
	 *
	 * @param nom : Le nom saisi par l'utilisateur pour lancer la recherche
	 * @return prog : Une variable de type ProgrammeurBean
	 *
	 */
	ProgrammeurBean getProgrammeur(String matricule) throws ProgrammeurInconnuExeption;

	/**
	 * Supprime un programmeur correspondant au matricule passé en paramètre
	 * 
	 * @param matricule : La matricule saisi par l'utilisateur utilisé pour lancer la recherche
	 *
	 */
	void deleteProgrammeur(String matricule);

	/**
	 * Ajoute un programmeur dans  la db
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
	 * Permet de mettre à jour les données d'un programmeur
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
	 * Permet de construire la chaîne de caractères qui sera
	 * affiché lorsqu'on choisit Programmeur - Afficher - Tous
	 *
	 * @return listeProg : chaîne de caractères de tout les programmeurs
	 *
	 */
	String afficherProgrammeurs();
	
	/**
	 * Permet de libérer les ressources liées à  la base de
	 * données
	 */
	void fermerRessources();
}
