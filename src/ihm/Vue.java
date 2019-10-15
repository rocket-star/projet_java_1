/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSpinner;
import javax.swing.SwingUtilities;
import data.ActionsBD;
import data.ActionsBDImpl;
import data.ProgrammeurBean;
import data.ProgrammeurInconnuExeption;

import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author mohamed
 */
public class Vue extends JFrame implements ActionListener {

	// Déclaration des attributs
	// L'initialisation se fera "en local" dans des méthodes

	private JTextArea zoneAffichageProgrammeurs;
	private JScrollPane scroll;
	private JMenuItem ActionQuitter;
	private JMenuItem AfficherTous;
	private JLabel ImageEfrei;
	private JMenu MenuAction;
	private JMenu MenuAfficher;
	private JMenuItem MenuAjouter;
	private JMenuItem MenuModifier;
	private JMenuBar MenuPrincipal;
	private JMenu MenuProgrammeur;
	private JMenuItem MenuSupprimer;
	private JPanel PanelFondEcran;
	private JPanel panelTous;
	private JPanel panelFormulaire;
	private JPanel panelSuppr;
	private JLabel nom;
	private JLabel dateEmbauche;
	private JLabel matricule;
	private JLabel adresse;
	private JLabel resp;
	private JLabel hobby;
	private JLabel prenom;
	private JLabel pseudo;
	private JLabel dateNaissance;
	private JLabel jour;
	private JSpinner jSpinner1;
	private JSpinner jSpinner2;
	private JSpinner jSpinner3;
	private JSpinner jSpinner4;
	private JSpinner jSpinner5;
	private JSpinner jSpinner6;
	private JTextField jTMatricule;
	private JTextField jTextField10;
	private JTextField jTextField11;
	private JTextField jTNom;
	private JTextField jTAdresse;
	private JTextField jTResp;
	private JTextField jTHobby;
	private JTextField jTPrenom;
	private JTextField jTPseudo;
	private JButton recherche;
	private JButton reinitialise;
	private JButton valider;
	private JButton annuler;
	private ActionsBD dt;
	private ProgrammeurBean progrBean;
	private boolean estAjouter;
	private boolean estSupprimer;
	private boolean estModifier;
	
	// End of variables declaration
	public void init() {
		accueil();
		// formulaire();
		// AfficherForm();

	}

	@Override
	public void actionPerformed(ActionEvent event) {

		if (event.getSource() == AfficherTous) {
			remove(PanelFondEcran);
			if (panelFormulaire != null) {
				remove(panelFormulaire);
			}
			estAjouter = false;
			estSupprimer = false;
			estModifier = false;
			panelTous = new JPanel();
			zoneAffichageProgrammeurs = new JTextArea(21, 60);
			scroll = new JScrollPane(zoneAffichageProgrammeurs);
			panelTous.add(scroll);
			this.add(panelTous);
			zoneAffichageProgrammeurs.setEnabled(false);
			dt = new ActionsBDImpl();
			String contenuTextArea = dt.afficherProgrammeurs();
			zoneAffichageProgrammeurs.setText(contenuTextArea);
			dt.fermerRessources();
			SwingUtilities.updateComponentTreeUI(this);
		}

		if (event.getSource() == MenuModifier) {
			if (panelFormulaire != null) {
				remove(panelFormulaire);
			}
			if (panelTous != null) {
				remove(panelTous);
			}
			estAjouter = false;
			estSupprimer = false;
			estModifier = true;
			remove(PanelFondEcran);
			formulaire();
			AfficherForm();
			reinitialise.setEnabled(false);
			valider.setEnabled(false);
			this.add(panelFormulaire);
			SwingUtilities.updateComponentTreeUI(this);

		}

		if (event.getSource() == MenuAjouter) {
			if (panelFormulaire != null) {
				remove(panelFormulaire);
			}
			if (panelTous != null) {
				remove(panelTous);
			}
			remove(PanelFondEcran);
			
			estAjouter = true;
			estSupprimer = false;
			estModifier = false;
			
			formulaire();
			AfficherForm();
			reinitialise.setEnabled(true);
			valider.setEnabled(true);
			recherche.setEnabled(false);
			this.add(panelFormulaire);
			SwingUtilities.updateComponentTreeUI(this);

		}

		if (event.getSource() == MenuSupprimer) {
			if (panelFormulaire != null) {
				remove(panelFormulaire);
			}
			if (panelTous != null) {
				remove(panelTous);
			}
			
			estAjouter = false;
			estSupprimer = true;
			estModifier = false;
			
			remove(PanelFondEcran);
			formulaire();
			AfficherForm();

			reinitialise.setEnabled(false);
			recherche.setEnabled(false);
			nom.setEnabled(false);
			jTNom.setEnabled(false);
			adresse.setEnabled(false);
			jTAdresse.setEnabled(false);
			resp.setEnabled(false);
			jTResp.setEnabled(false);
			hobby.setEnabled(false);
			jTHobby.setEnabled(false);
			prenom.setEnabled(false);
			jTPrenom.setEnabled(false);
			pseudo.setEnabled(false);
			jTPseudo.setEnabled(false);
			dateEmbauche.setEnabled(false);
			jTextField11.setEnabled(false);
			dateNaissance.setEnabled(false);
			jSpinner1.setEnabled(false);
			jSpinner2.setEnabled(false);
			jSpinner3.setEnabled(false);
			jSpinner4.setEnabled(false);
			jSpinner5.setEnabled(false);
			jSpinner6.setEnabled(false);
			recherche.setEnabled(true);
			this.add(panelFormulaire);
			SwingUtilities.updateComponentTreeUI(this);
		}
		if(event.getSource() == ActionQuitter) {
			int dialogResult = JOptionPane.showConfirmDialog (this, "Vérification","Voulez-vous vraiment quitter ?",JOptionPane.YES_NO_OPTION);
			if(dialogResult == JOptionPane.YES_OPTION){
				this.dispose();
			}
		}
	}

	// IF MATRICULE INTROUVABLE
	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		if (evt.getSource() == recherche) {
			dt = new ActionsBDImpl();
			try {
				progrBean = dt.getProgrammeur(this.jTMatricule.getText());
				this.jTNom.setText(progrBean.getNom());
				this.jTPrenom.setText(progrBean.getPrenom());
				this.jTAdresse.setText(progrBean.getAdresse());
				this.jTHobby.setText(progrBean.getHobby());
				this.jTPseudo.setText(progrBean.getPseudo());
				this.jTPseudo.setText(progrBean.getPseudo());
				this.jTResp.setText(progrBean.getResponsable());
				Calendar cal = Calendar.getInstance();
				cal.setTime(progrBean.getDateNaiss());
				this.jSpinner1.setValue(cal.get(Calendar.DAY_OF_MONTH));
				this.jSpinner2.setValue(cal.get(Calendar.MONTH)+1);
				this.jSpinner3.setValue(cal.get(Calendar.YEAR));
				cal.setTime(progrBean.getDateEmbauche());
				this.jSpinner4.setValue(cal.get(Calendar.DAY_OF_MONTH));
				this.jSpinner5.setValue(cal.get(Calendar.MONTH)+1);
				this.jSpinner6.setValue(cal.get(Calendar.YEAR));
				if(estModifier) {
					reinitialise.setEnabled(true);
					valider.setEnabled(true);
				}else if(estSupprimer) {
					reinitialise.setEnabled(false);
					valider.setEnabled(true);
				}
			} catch (ProgrammeurInconnuExeption e) {
				JOptionPane.showMessageDialog(this, e, "Echec", JOptionPane.ERROR_MESSAGE);
			}
			dt.fermerRessources();
		}
	}

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		if (evt.getSource() == annuler) {

			remove(panelFormulaire);
			accueil();
			SwingUtilities.updateComponentTreeUI(this);
		} // TODO add your handling code here:
	}

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
		if (evt.getSource() == valider) {
			dt = new ActionsBDImpl();
			if(estAjouter) {
				String matricule = jTMatricule.getText();
				String nom = jTNom.getText();
				String prenom = jTPrenom.getText();
				String responsable = jTResp.getText();
				String pseudo = jTPseudo.getText();
				String hobby = jTHobby.getText();
				String adresse = jTAdresse.getText();
				String dateDeNaissance = jSpinner1.getValue().toString() +"/" +jSpinner2.getValue().toString()+"/"+jSpinner3.getValue().toString();
				String dateEmbauche = jSpinner4.getValue().toString() +"/" +jSpinner5.getValue().toString()+"/"+jSpinner6.getValue().toString();
				dt.insertProgrammeur(matricule, nom, prenom, adresse, pseudo, responsable, hobby, dateDeNaissance, dateEmbauche);
			}else if(estModifier) {
				String matricule = jTMatricule.getText();
				String nom = jTNom.getText();
				String prenom = jTPrenom.getText();
				String responsable = jTResp.getText();
				String pseudo = jTPseudo.getText();
				String hobby = jTHobby.getText();
				String adresse = jTAdresse.getText();
				String dateDeNaissance = jSpinner1.getValue().toString() +"/" +jSpinner2.getValue().toString()+"/"+jSpinner3.getValue().toString();
				String dateEmbauche = jSpinner4.getValue().toString() +"/" +jSpinner5.getValue().toString()+"/"+jSpinner6.getValue().toString();
				dt.updateProgrammeur(matricule, nom, prenom, adresse, pseudo, responsable, hobby, dateDeNaissance, dateEmbauche);;
			}
		}
		dt.fermerRessources();
	}

	private void accueil() {

		PanelFondEcran = new JPanel(); // Créantion d'un panel pour gérer les widgets
		PanelFondEcran.setLayout(null);
		// Insets insets = PanelFondEcran.getInsets();

		ImageEfrei = new JLabel(
				new ImageIcon("C:\\Users\\dell\\eclipse-workspace\\javaProjetV2\\Logo-Efrei-Paris-2017.jpg"));
		Dimension size = ImageEfrei.getPreferredSize();
		size = ImageEfrei.getPreferredSize();
		ImageEfrei.setBounds(100, 70, size.width, size.height);
		MenuPrincipal = new JMenuBar();
		MenuProgrammeur = new JMenu("Programmeur");
		MenuSupprimer = new JMenuItem("Supprimer");
		MenuModifier = new JMenuItem("Modifier");
		MenuAction = new JMenu("Action");
		MenuAjouter = new JMenuItem("Ajouter");
		MenuAfficher = new JMenu("Afficher");
		AfficherTous = new JMenuItem("Tous");
		ActionQuitter = new JMenuItem("Quitter");

		this.setVisible(true);
		this.setTitle("GesProg");
		setDefaultCloseOperation(EXIT_ON_CLOSE); // Fermeture fenêtre = arrêt de l'application
		setBounds(10, 10, 750, 400);
		PanelFondEcran.setBackground(Color.WHITE);
		ImageEfrei.setIcon(new ImageIcon("Logo-Efrei-Paris-2017.jpg"));

		MenuPrincipal.add(MenuProgrammeur);
		MenuPrincipal.add(MenuAction);

		MenuProgrammeur.add(MenuAjouter);
		MenuProgrammeur.add(MenuModifier);
		MenuProgrammeur.add(MenuSupprimer);
		MenuProgrammeur.add(MenuAfficher);

		MenuAfficher.add(AfficherTous);

		MenuAction.add(ActionQuitter);
		PanelFondEcran.add(ImageEfrei);
		this.add(PanelFondEcran);
		setJMenuBar(MenuPrincipal);

		AfficherTous.addActionListener(this);
		MenuModifier.addActionListener(this);
		MenuSupprimer.addActionListener(this);
		MenuAjouter.addActionListener(this);
		ActionQuitter.addActionListener(this);
		
		estAjouter = false;
		estSupprimer = false;
		estModifier = false;
		
		SwingUtilities.updateComponentTreeUI(this);

	}

	private void formulaire() {
		matricule = new JLabel("Matricule");
		nom = new JLabel("Nom");
		adresse = new JLabel("Adresse");
		resp = new JLabel("Résponsable");
		hobby = new JLabel("Hobby");
		prenom = new JLabel("Prénom");
		pseudo = new JLabel("Pseudo");
		dateNaissance = new JLabel("Date de Naissance");
		jour = new JLabel("Jour");
		jTMatricule = new JTextField("0");
		jTNom = new JTextField();
		jTAdresse = new JTextField();
		jTResp = new JTextField();
		jTHobby = new JTextField();
		jTPrenom = new JTextField();
		jTPseudo = new JTextField();
		jTextField10 = new JTextField();
		jTextField11 = new JTextField();
		dateEmbauche = new JLabel("Date d'embauche");
		SpinnerNumberModel model1 = new SpinnerNumberModel(1, 1, 31, 1);
		SpinnerNumberModel model11 = new SpinnerNumberModel(1, 1, 31, 1);
		SpinnerNumberModel model2 = new SpinnerNumberModel(1, 1, 12, 1);
		SpinnerNumberModel model22 = new SpinnerNumberModel(1, 1, 12, 1);
		SpinnerNumberModel model3 = new SpinnerNumberModel(1945, 1945, 2019, 1);
		SpinnerNumberModel model33 = new SpinnerNumberModel(1945, 1945, 2019, 1);
		jSpinner4 = new JSpinner(model1);
		jSpinner5 = new JSpinner(model22);
		jSpinner6 = new JSpinner(model3);
		jSpinner1 = new JSpinner(model11);
		jSpinner2 = new JSpinner(model2);
		jSpinner3 = new JSpinner(model33);
		recherche = new JButton("Rechercher");
		reinitialise = new JButton("Réinitialiser");
		valider = new JButton("Valider");
		annuler = new JButton("Annuler");
		jTextField10.setText("M");

		Dimension size = matricule.getPreferredSize();
		matricule.setBounds(10, 10, size.width, size.height);

		size = jTMatricule.getPreferredSize();
		jTMatricule.setBounds(70, 7, 70 + size.width, size.height);

		size = nom.getPreferredSize();
		nom.setBounds(100, 90, size.width, size.height);

		size = jTNom.getPreferredSize();
		jTNom.setBounds(190, 85, 120 + size.width, size.height);

		size = adresse.getPreferredSize();
		adresse.setBounds(100, 130, size.width, size.height);

		size = jTAdresse.getPreferredSize();
		jTAdresse.setBounds(190, 125, 120 + size.width, size.height);

		size = resp.getPreferredSize();
		resp.setBounds(100, 170, size.width, size.height);

		size = jTResp.getPreferredSize();
		jTResp.setBounds(190, 165, 120 + size.width, size.height);

		size = hobby.getPreferredSize();
		hobby.setBounds(390, 170, size.width, size.height);

		size = jTHobby.getPreferredSize();
		jTHobby.setBounds(480, 165, 120 + size.width, size.height);

		size = prenom.getPreferredSize();
		prenom.setBounds(390, 90, size.width, size.height);

		size = jTPrenom.getPreferredSize();
		jTPrenom.setBounds(480, 85, 120 + size.width, size.height);

		size = pseudo.getPreferredSize();
		pseudo.setBounds(390, 130, size.width, size.height);

		size = jTPseudo.getPreferredSize();
		jTPseudo.setBounds(480, 125, 120 + size.width, size.height);

		size = dateNaissance.getPreferredSize();
		dateNaissance.setBounds(100, 225, 70 + size.width, size.height);

		size = jSpinner1.getPreferredSize();
		jSpinner1.setBounds(230, 220, 7 + size.width, size.height);

		size = jSpinner2.getPreferredSize();
		jSpinner2.setBounds(290, 220, 7 + size.width, size.height);

		size = jSpinner3.getPreferredSize();
		jSpinner3.setBounds(350, 220, 7 + size.width, size.height);

		size = jSpinner4.getPreferredSize();
		jSpinner4.setBounds(230, 255, 7 + size.width, size.height);

		size = jSpinner5.getPreferredSize();
		jSpinner5.setBounds(290, 255, 7 + size.width, size.height);

		size = jSpinner6.getPreferredSize();
		jSpinner6.setBounds(350, 255, 7 + size.width, size.height);

		size = dateEmbauche.getPreferredSize();
		dateEmbauche.setBounds(100, 260, 70 + size.width, size.height);

		size = recherche.getPreferredSize();
		recherche.setBounds(90, 320, 20 + size.width, size.height);

		size = reinitialise.getPreferredSize();
		reinitialise.setBounds(250, 320, 20 + size.width, size.height);
		reinitialise.setEnabled(false);

		size = valider.getPreferredSize();
		valider.setBounds(410, 320, 20 + size.width, size.height);
		valider.setEnabled(false);
		size = annuler.getPreferredSize();
		annuler.setBounds(540, 320, 20 + size.width, size.height);

	}

	private void AfficherForm() {
		panelFormulaire = new JPanel();
		panelFormulaire.setLayout(null);
		setBounds(10, 10, 750, 450);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Insets insets = panelModifier.getInsets();

		panelFormulaire.add(matricule);
		panelFormulaire.add(jTMatricule);
		panelFormulaire.add(nom);
		panelFormulaire.add(jTNom);
		panelFormulaire.add(adresse);
		panelFormulaire.add(jTAdresse);
		panelFormulaire.add(resp);
		panelFormulaire.add(jTResp);
		panelFormulaire.add(hobby);
		panelFormulaire.add(jTHobby);
		panelFormulaire.add(prenom);
		panelFormulaire.add(jTPrenom);
		panelFormulaire.add(pseudo);
		panelFormulaire.add(jTPseudo);
		panelFormulaire.add(dateNaissance);
		panelFormulaire.add(jour);
		panelFormulaire.add(dateEmbauche);
		panelFormulaire.add(jSpinner1);
		panelFormulaire.add(jSpinner2);
		panelFormulaire.add(jSpinner3);
		panelFormulaire.add(jSpinner4);
		panelFormulaire.add(jSpinner5);
		panelFormulaire.add(jSpinner6);
		panelFormulaire.add(jTextField10);
		panelFormulaire.add(jTextField11);
		panelFormulaire.add(recherche);
		panelFormulaire.add(valider);
		panelFormulaire.add(annuler);
		panelFormulaire.add(reinitialise);

		recherche.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		valider.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});

		annuler.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		this.add(panelFormulaire);
		SwingUtilities.updateComponentTreeUI(this);
	}

/**	public void test(ActionEvent event) {
		if (event.getSource() == btnAfficherTous) {
			dt = new ActionsBDImpl();
			contenuTextArea = dt.afficherProgrammeurs();
			zoneAffichageProgrammeurs.setText(contenuTextArea);
			dt.fermerRessources();
		} else if (event.getSource() == btnRechercher) {
			dt = new ActionsBDImpl();
			try {
				progrBean = dt.getProgrammeur(this.champNom.getText());
				contenuTextArea = progrBean.toString();
				zoneAffichageProgrammeurs.setText(contenuTextArea);
			} catch (ProgrammeurInconnuExeption e) {
				JOptionPane.showMessageDialog(this, e, "Echec", JOptionPane.ERROR_MESSAGE);
			}
			dt.fermerRessources();
		}
	}**/
}
