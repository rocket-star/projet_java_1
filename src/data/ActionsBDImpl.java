package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import myutil.Constantes;

/**
 * Class  ActionsBDImpl
 * @author FERNANDO, MARIMOUTTOU, GHALMI
 * @version 1.1
 */
public class ActionsBDImpl implements ActionsBD {

	private Connection dbConn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private ArrayList<ProgrammeurBean> listeProgrammeurs;
	private ProgrammeurBean prog;

	/**
	 * Ce constructeur permet d'initialiser la connexion
	 */
	public ActionsBDImpl() {
		try {
			dbConn = DriverManager.getConnection(Constantes.URL, Constantes.USER, Constantes.MDP);
		} catch (SQLException sqle) {
			Logger.getLogger(ActionsBDImpl.class.getName()).log(Level.SEVERE, null, sqle);
		}
	}

	/**
	 * Lance la requete passée en paramètre et retourne le ResultSet
	 * correspondant à cette requête
	 *
	 * @param req La requête SQL que l'on souhaite exécuter
	 *            
	 * @return rs Une variable de type ResultSet
	 */
	public ResultSet getResultSet(String req) {
		try {
			stmt = dbConn.createStatement();
			rs = stmt.executeQuery(req);
		} catch (SQLException sqle) {
			Logger.getLogger(ActionsBDImpl.class.getName()).log(Level.SEVERE, null, sqle);
		}
		return rs;
	}

	/**
	 * Cette méthode récupère toutes les infos d'un programmeur et retourne une
	 * liste de l'ensemble des programmeurs
	 *
	 * @return listeProgrammeurs : Une variable de type ArryList
	 */
	public ArrayList getProgrammeurs() {
		rs = this.getResultSet(Constantes.REQUETE_TOUS);
		listeProgrammeurs = new ArrayList<>();

		try {
			while (rs.next()) {
				prog = new ProgrammeurBean();
				prog.setMatricule(rs.getString("MATRICULE"));
				prog.setPrenom(rs.getString("PRENOM"));
				prog.setNom(rs.getString("NOM"));
				prog.setAdresse(rs.getString("ADRESSE"));
				prog.setPseudo(rs.getString("PSEUDO"));
				prog.setResponsable(rs.getString("RESPONSABLE"));
				prog.setHobby(rs.getString("HOBBY"));
				prog.setDateNaiss(new Date(rs.getDate("DATE_NAISS").getTime()));
				prog.setDateEmbauche(new Date(rs.getDate("DATE_EMB").getTime()));
				listeProgrammeurs.add(prog);
			}
		} catch (SQLException sqle) {
			Logger.getLogger(ActionsBDImpl.class.getName()).log(Level.SEVERE, null, sqle);
		}
		return listeProgrammeurs;
	}

	/**
	 * Cette méthode récupère toutes les infos d'un programmeur et retourne ce
	 * programmeur sous la forme d'un Java Bean Cette méthode est utilisée pour
	 * rechercher un progammeur via son matricule
	 *
	 * @param nom : Le nom saisi par l'utilisateur pour lancer la recherche
	 * @return prog : Une variable de type ProgrammeurBean
	 *
	 */
	public ProgrammeurBean getProgrammeur(String matricule) throws ProgrammeurInconnuExeption {
		try {
			pstmt = dbConn.prepareStatement(Constantes.REQUETE_UNIQUE);
			pstmt.setString(1, matricule);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				prog = new ProgrammeurBean();
				prog.setMatricule(rs.getString("MATRICULE"));
				prog.setPrenom(rs.getString("PRENOM"));
				prog.setNom(rs.getString("NOM"));
				prog.setAdresse(rs.getString("ADRESSE"));
				prog.setPseudo(rs.getString("PSEUDO"));
				prog.setResponsable(rs.getString("RESPONSABLE"));
				prog.setHobby(rs.getString("HOBBY"));
				prog.setDateNaiss(new Date(rs.getDate("DATE_NAISS").getTime()));
				prog.setDateEmbauche(new Date(rs.getDate("DATE_EMB").getTime()));
			}
			if (prog == null) {
				throw new ProgrammeurInconnuExeption(matricule);
			}
		} catch (SQLException sqle) {
			Logger.getLogger(ActionsBDImpl.class.getName()).log(Level.SEVERE, null, sqle);
		}
		return prog;
	}

	/**
	 * Cette méthode supprime un programmeur correspondant au matricule passé en paramètre
	 * 
	 * @param matricule : La matricule saisi par l'utilisateur utilisé pour lancer la recherche
	 *
	 */
	public void deleteProgrammeur(String matricule) {
		try {
			pstmt = dbConn.prepareStatement(Constantes.REQUETE_DELETE);
			pstmt.setString(1, matricule);
			rs = pstmt.executeQuery();
		} catch (SQLException sqle) {
			Logger.getLogger(ActionsBDImpl.class.getName()).log(Level.SEVERE, null, sqle);
		}
	}

	/**
	 * Cette méthode ajoute un programmeur à la db
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
	public void insertProgrammeur(String matricule, String nom, String prenom, String adresse, String pseudo,
			String responsable, String hobby, java.sql.Date dateDeNaissance, java.sql.Date dateEmbauche) {
		try {
			pstmt = dbConn.prepareStatement(Constantes.REQUETE_INSERT);
			pstmt.setString(1, matricule);
			pstmt.setString(2, nom);
			pstmt.setString(3, prenom);
			pstmt.setString(4, adresse);
			pstmt.setString(5, pseudo);
			pstmt.setString(6, responsable);
			pstmt.setString(7, hobby);
			pstmt.setDate(8,dateDeNaissance);
			pstmt.setDate(9, dateEmbauche);
			rs = pstmt.executeQuery();
		} catch (SQLException sqle) {
			Logger.getLogger(ActionsBDImpl.class.getName()).log(Level.SEVERE, null, sqle);
		}
	}
	
	/**
	 * Cette méthode permet de mettre à jour les données d'un programmeur
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
	public void updateProgrammeur(String matricule, String nom, String prenom, String adresse, String pseudo,
			String responsable, String hobby, java.sql.Date dateDeNaissance, java.sql.Date dateEmbauche) {
		try {
			pstmt = dbConn.prepareStatement(Constantes.REQUETE_UPDATE);
			pstmt.setString(1, nom);
			pstmt.setString(2, prenom);
			pstmt.setString(3, adresse);
			pstmt.setString(4, pseudo);
			pstmt.setString(5, responsable);
			pstmt.setString(6, hobby);
			pstmt.setDate(7,dateDeNaissance);
			pstmt.setDate(8, dateEmbauche);
			pstmt.setString(9, matricule);
			rs = pstmt.executeQuery();
		} catch (SQLException sqle) {
			Logger.getLogger(ActionsBDImpl.class.getName()).log(Level.SEVERE, null, sqle);
		}
	}

	/**
	 * Cette méthode permet de construire la chaîne de caractères qui sera
	 * affichée lorsqu'on choisit Programmeur - Afficher - Tous
	 *
	 * @return listeProg : Une variable de type String
	 *
	 */
	public String afficherProgrammeurs() {
		String listeProg = "";

		listeProgrammeurs = this.getProgrammeurs();
		for (ProgrammeurBean progr : listeProgrammeurs) {
			listeProg = listeProg + progr;
		}
		return listeProg;
	}

	/**
	 * Cette méthode permet de libérer les ressources liées à la base de
	 * données
	 */
	public void fermerRessources() {
		if (dbConn != null) {
			try {
				dbConn.close();
				if (stmt != null) {
					stmt.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				dbConn = null;
			} catch (SQLException ex) {
				Logger.getLogger(ActionsBDImpl.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

}
