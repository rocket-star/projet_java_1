package data;

import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.Date;

/**
 * Class ProgrammeurBean
 * 
 * @author FERNANDO, MARIMOUTTOU, GHALMI
 * @version 1.1
 */
public class ProgrammeurBean {
	private String matricule;
	private String nom;
	private String prenom;
	private String adresse;
	private String pseudo;
	private String responsable;
	private String hobby;
	private Date dateNaiss;
	private Date dateEmbauche;
	private int id;

	public ProgrammeurBean() {
	}

	@Override
	public String toString() {
		String affichage = this.id + " " + this.matricule + " " + this.nom + " " + this.prenom + " " + this.adresse
				+ " " + this.pseudo + " " + this.responsable + " " + this.hobby + " "
				+ new SimpleDateFormat("yyyy-MM-dd").format(this.dateNaiss) + " "
				+ new SimpleDateFormat("yyyy-MM-dd").format(this.dateEmbauche) + "\n";

		return affichage;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public Date getDateNaiss() {
		return dateNaiss;
	}

	public void setDateNaiss(Date dateNaiss) {
		this.dateNaiss = dateNaiss;
	}

	public Date getDateEmbauche() {
		return dateEmbauche;
	}

	public void setDateEmbauche(Date dateEmbauche) {
		this.dateEmbauche = dateEmbauche;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
