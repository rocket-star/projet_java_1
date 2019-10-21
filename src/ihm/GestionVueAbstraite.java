package ihm;

import java.text.ParseException;

import javax.swing.JFrame;

public abstract class GestionVueAbstraite extends JFrame{

	public abstract void jButtonRecherhcerActionPerformed(java.awt.event.ActionEvent evt);
	
	public abstract void jButtonAnnulerActionPerformed(java.awt.event.ActionEvent evt);
	
	public abstract void jButtonValiderActionPerformed(java.awt.event.ActionEvent evt) throws ParseException;
	
	public abstract void jButtonReinitialiserActionPerformed(java.awt.event.ActionEvent evt);
	
	public abstract void viderChamps();
	
	public abstract void formulaire();
	
	public abstract void AfficherForm();
	
	public abstract void accueil();
}
