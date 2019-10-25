package ihm;

import java.text.ParseException;

import javax.swing.JFrame;

public abstract class GestionVueAbstraite extends JFrame{

	/**
	 * Méthode déclenchée lors du clic sur le bouton rechercher
	 * @param evt
	 */
	public abstract void jButtonRecherhcerActionPerformed(java.awt.event.ActionEvent evt);

	/**
	 * Méthode déclenchée lors du clic sur le bouton annuler
	 * @param evt
	 */
	public abstract void jButtonAnnulerActionPerformed(java.awt.event.ActionEvent evt);
	
	/**
	 * Méthode déclenchée lors du clic sur le bouton valider
	 * @param evt
	 */
	public abstract void jButtonValiderActionPerformed(java.awt.event.ActionEvent evt) throws ParseException;
	
	/**
	 * Méthode déclenchée lors du clic sur le bouton reinitialiser
	 * @param evt
	 */
	public abstract void jButtonReinitialiserActionPerformed(java.awt.event.ActionEvent evt);
	
	/**
	 * Réinitialise le formulaire à l'état initial
	 */
	public abstract void viderChamps();
	
	/**
	 * Initialise et dimensionne les attributs qui concerne le formulaire
	 */
	public abstract void formulaire();
	
	/**
	 * Ajoute les éléments du formulaire au panel et affiche le formulaire
	 */
	public abstract void AfficherForm();
	
	/**
	 * Affiche l'accueil de l'application
	 */
	public abstract void accueil();
	
	/**
	 * Initialise la vue de l'application
	 */
	public abstract void init();
}
