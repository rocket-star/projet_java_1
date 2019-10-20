package data;

/**
 * Class d'exception ProgrammeurInconnuExeption
 * @author FERNANDO, MARIMOUTTOU, GHALMI
 * @version 1.1
 */
public class ProgrammeurInconnuExeption extends Exception {
	private String matricule;
	
	public ProgrammeurInconnuExeption(String matricule) {
		this.matricule = matricule;
	}
	
	public String toString() {
		return "Le Programmeur n�: "+this.matricule+" n'existe pas.";
	}
}
