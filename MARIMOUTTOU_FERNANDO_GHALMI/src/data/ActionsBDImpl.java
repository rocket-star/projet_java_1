package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import myutil.Constantes;

/**
 * Class ActionsBDImpl
 * 
 * @author FERNANDO, MARIMOUTTOU, GHALMI
 * @version 1.1
 */
public class ActionsBDImpl implements ActionsBD {

	private Connection dbConn;
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

	public ResultSet getResultSet(String req) {
		try {
			pstmt = dbConn.prepareStatement(req);
			rs = pstmt.executeQuery();
		} catch (SQLException sqle) {
			Logger.getLogger(ActionsBDImpl.class.getName()).log(Level.SEVERE, null, sqle);
		}
		return rs;
	}

	public ArrayList<ProgrammeurBean> getProgrammeurs() {
		rs = this.getResultSet(Constantes.REQUETE_TOUS);
		listeProgrammeurs = new ArrayList<>();

		try {
			while (rs.next()) {
				prog = new ProgrammeurBean();
				prog.setId(rs.getInt("ID"));
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
	 * Récupère toutes les infos d'un programmeur et retourne ce programmeur sous
	 * la forme d'un Java Bean Cette méthode est utilisée pour rechercher un
	 * progammeur via son matricule
	 *
	 * @param nom
	 *            : Le nom saisi par l'utilisateur pour lancer la recherche
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

	public void deleteProgrammeur(String matricule) {
		try {
			pstmt = dbConn.prepareStatement(Constantes.REQUETE_DELETE);
			pstmt.setString(1, matricule);
			rs = pstmt.executeQuery();
		} catch (SQLException sqle) {
			Logger.getLogger(ActionsBDImpl.class.getName()).log(Level.SEVERE, null, sqle);
		}
	}

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
			pstmt.setDate(8, dateDeNaissance);
			pstmt.setDate(9, dateEmbauche);
			rs = pstmt.executeQuery();
		} catch (SQLException sqle) {
			Logger.getLogger(ActionsBDImpl.class.getName()).log(Level.SEVERE, null, sqle);
		}
	}

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
			pstmt.setDate(7, dateDeNaissance);
			pstmt.setDate(8, dateEmbauche);
			pstmt.setString(9, matricule);
			rs = pstmt.executeQuery();
		} catch (SQLException sqle) {
			Logger.getLogger(ActionsBDImpl.class.getName()).log(Level.SEVERE, null, sqle);
		}
	}

	public String afficherProgrammeurs() {
		String listeProg = "";

		listeProgrammeurs = this.getProgrammeurs();
		for (ProgrammeurBean progr : listeProgrammeurs) {
			listeProg = listeProg + progr;
		}
		return listeProg;
	}

	public void fermerRessources() {
		if (dbConn != null) {
			try {
				dbConn.close();
				if (pstmt != null) {
					pstmt.close();
				}
				dbConn = null;
			} catch (SQLException ex) {
				Logger.getLogger(ActionsBDImpl.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	@Override
	public void initBD() {
		String line;
		StringBuilder s = new StringBuilder();
		try {
			FileReader file = new FileReader(new File(Constantes.SCRIPT));
			BufferedReader buff = new BufferedReader(file);
			while ((line = buff.readLine()) != null) {
				s.append(line);//lecture du fichier script.txt
			}
			buff.close();
			String[] sql = s.toString().split(";");//creation d'un tableau constitué de chaque requette

			pstmt = dbConn.prepareStatement(Constantes.TABLE_EXISTS);//On verifie si la table existe déja
			ResultSet res = pstmt.executeQuery();
			if (res.next()) {
				pstmt = dbConn.prepareStatement(sql[0]);//si la table existe on la supprime
				pstmt.executeQuery();
			}

			pstmt = dbConn.prepareStatement(Constantes.SEQUENCE_EXISTS);//On verifie si la sequence existe déja 
			res = pstmt.executeQuery();
			if (res.next()) {
				pstmt = dbConn.prepareStatement(sql[1]);//si la sequence existe on la supprime
				pstmt.executeQuery();
			}

			for (int i = 2; i < sql.length; i++) {
				if (!sql[i].equals("")) {
					pstmt = dbConn.prepareStatement(sql[i]);
					pstmt.executeQuery();//on execute chaque requette une a une
				}
			}
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}
}
