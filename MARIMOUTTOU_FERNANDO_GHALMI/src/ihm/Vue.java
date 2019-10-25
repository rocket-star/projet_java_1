package ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JButton;
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
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import data.ActionsBD;
import data.ActionsBDImpl;
import data.ProgrammeurBean;
import data.ProgrammeurInconnuExeption;
import myutil.Constantes;

import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;

/**
 * Class Vue
 * 
 * @author FERNANDO, MARIMOUTTOU, GHALMI
 * @version 1.1
 */
public class Vue extends GestionVueAbstraite implements ActionListener {

	// Déclaration des attributs
	// L'initialisation se fera "en local" dans des méthodes
	private JTable zoneAffichageProgrammeurs;
	private JLabel nbProgrammeurs;
	private JScrollPane scroll;
	private JMenuItem actionQuitter;
	private JMenuItem actionInitBD;
	private JMenuItem afficherTous;
	private JLabel imageEfrei;
	private JMenu menuAction;
	private JMenu menuAfficher;
	private JMenuItem menuAjouter;
	private JMenuItem menuModifier;
	private JMenuBar menuPrincipal;
	private JMenu menuProgrammeur;
	private JMenuItem menuSupprimer;
	private JPanel panelFondEcran;
	private JPanel panelTous;
	private JPanel panelFormulaire;
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
	private JSpinner jSpinnerJourN;
	private JSpinner jSpinnerMoisN;
	private JSpinner jSpinnerAnN;
	private JSpinner jSpinnerJourE;
	private JSpinner jSpinnerMoisE;
	private JSpinner jSpinnerAnE;
	private JTextField jTMatricule;
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
	private JButton retour;
	private ActionsBD dt;
	private ProgrammeurBean progrBean;
	private JLabel bande;
	private boolean estAjouter;
	private boolean estSupprimer;
	private boolean estModifier;
	// Fin de declaration des variables

	public void init() {
		Constantes.init();
		accueil();
	}

	public void accueil() {

		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		panelFondEcran = new JPanel(); // Création d'un panel pour gérer les widgets
		panelFondEcran.setLayout(null);

		imageEfrei = new JLabel(new ImageIcon("Logo-Efrei-Paris-2017.jpg"));
		Dimension size = imageEfrei.getPreferredSize();
		size = imageEfrei.getPreferredSize();
		imageEfrei.setBounds(100, 70, size.width, size.height);
		
		menuPrincipal = new JMenuBar();
		menuProgrammeur = new JMenu("Programmeur");
		menuSupprimer = new JMenuItem("Supprimer");
		menuModifier = new JMenuItem("Modifier");
		menuAction = new JMenu("Action");
		menuAjouter = new JMenuItem("Ajouter");
		menuAfficher = new JMenu("Afficher");
		afficherTous = new JMenuItem("Tous");
		actionQuitter = new JMenuItem("Quitter");
		actionInitBD = new JMenuItem("Initialiser la BD");

		this.setVisible(true);
		this.setTitle("GesProg");
		this.setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE); // Fermeture fenetre = arret de l'application
		setBounds(10, 10, 750, 420);
		panelFondEcran.setBackground(Color.WHITE); // Couleur de fond
		imageEfrei.setIcon(new ImageIcon("Logo-Efrei-Paris-2017.jpg")); // Image de fond

		menuPrincipal.add(menuProgrammeur);
		menuPrincipal.add(menuAction);
		
		menuProgrammeur.add(menuAfficher);
		menuAfficher.add(afficherTous);
		menuProgrammeur.add(menuModifier);
		menuProgrammeur.add(menuSupprimer);
		menuProgrammeur.add(menuAjouter);
		
		menuAction.add(actionQuitter);
		menuAction.add(actionInitBD);
		
		panelFondEcran.add(imageEfrei);
		this.add(panelFondEcran);
		
		setJMenuBar(menuPrincipal);

		// ici on effectue l'enregistrement des widget
		afficherTous.addActionListener(this);
		menuModifier.addActionListener(this);
		menuSupprimer.addActionListener(this);
		menuAjouter.addActionListener(this);
		actionQuitter.addActionListener(this);
		actionInitBD.addActionListener(this);

		estAjouter = false;
		estSupprimer = false;
		estModifier = false;

		SwingUtilities.updateComponentTreeUI(this);

	}

	// Cette méthode initialise et dimensionne les attributs qui concerne le
	// formulaire
	public void formulaire() {
		bande = new JLabel();
		matricule = new JLabel("Matricule");
		matricule.setForeground(Color.WHITE);
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
		dateEmbauche = new JLabel("Date d'embauche");
		SpinnerNumberModel model1 = new SpinnerNumberModel(1, 1, 31, 1);
		SpinnerNumberModel model11 = new SpinnerNumberModel(1, 1, 31, 1);
		SpinnerNumberModel model2 = new SpinnerNumberModel(1, 1, 12, 1);
		SpinnerNumberModel model22 = new SpinnerNumberModel(1, 1, 12, 1);
		SpinnerNumberModel model3 = new SpinnerNumberModel(1945, 1945, 2019, 1);
		SpinnerNumberModel model33 = new SpinnerNumberModel(1945, 1945, 2019, 1);
		jSpinnerJourE = new JSpinner(model1);
		jSpinnerMoisE = new JSpinner(model22);
		jSpinnerAnE = new JSpinner(model3);
		jSpinnerJourN = new JSpinner(model11);
		jSpinnerMoisN = new JSpinner(model2);
		jSpinnerAnN = new JSpinner(model33);
		recherche = new JButton("Rechercher");
		reinitialise = new JButton("Réinitialiser");
		valider = new JButton("Valider");
		annuler = new JButton("Annuler");

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

		size = jSpinnerJourN.getPreferredSize();
		jSpinnerJourN.setBounds(230, 220, 7 + size.width, size.height);

		size = jSpinnerMoisN.getPreferredSize();
		jSpinnerMoisN.setBounds(290, 220, 7 + size.width, size.height);

		size = jSpinnerAnN.getPreferredSize();
		jSpinnerAnN.setBounds(350, 220, 7 + size.width, size.height);

		size = jSpinnerJourE.getPreferredSize();
		jSpinnerJourE.setBounds(230, 255, 7 + size.width, size.height);

		size = jSpinnerMoisE.getPreferredSize();
		jSpinnerMoisE.setBounds(290, 255, 7 + size.width, size.height);

		size = jSpinnerAnE.getPreferredSize();
		jSpinnerAnE.setBounds(350, 255, 7 + size.width, size.height);

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

		size = bande.getPreferredSize();
		bande.setBounds(0, 0, 800 + size.width, 40 + size.height);
		bande.setBackground(Color.DARK_GRAY);
		bande.setOpaque(true);
	}

	public void AfficherForm() {
		panelFormulaire = new JPanel();
		panelFormulaire.setLayout(null);
		setBounds(10, 10, 750, 450);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// ajout des widgets au panel du formulaire
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
		panelFormulaire.add(jSpinnerJourN);
		panelFormulaire.add(jSpinnerMoisN);
		panelFormulaire.add(jSpinnerAnN);
		panelFormulaire.add(jSpinnerJourE);
		panelFormulaire.add(jSpinnerMoisE);
		panelFormulaire.add(jSpinnerAnE);
		panelFormulaire.add(recherche);
		panelFormulaire.add(valider);
		panelFormulaire.add(annuler);
		panelFormulaire.add(reinitialise);
		panelFormulaire.add(bande);

		// ajout des actionListener liés aux boutons du formulaire
		recherche.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonRecherhcerActionPerformed(evt);
			}
		});

		valider.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					jButtonValiderActionPerformed(evt);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		});

		annuler.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonAnnulerActionPerformed(evt);
			}
		});
		reinitialise.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonReinitialiserActionPerformed(evt);
			}
		});

		this.add(panelFormulaire);
		SwingUtilities.updateComponentTreeUI(this);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == afficherTous) {
			if (panelFondEcran != null) {
				remove(panelFondEcran);
			}
			if (panelFormulaire != null) {
				remove(panelFormulaire);
			}
			retour = new JButton("Retour");
			Dimension size = retour.getPreferredSize();
			retour.setBounds(450, 250, 20 + size.width, size.height);
			retour.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					jButtonAnnulerActionPerformed(evt);
				}
			});

			estAjouter = false;
			estSupprimer = false;
			estModifier = false;

			JLabel titre = new JLabel();
			titre.setText("LISTE-DES-PROGRAMMEURS");
			size = titre.getPreferredSize();
			titre.setBounds(10, 2, size.width +10, size.height +3);

			panelTous = new JPanel();
			panelTous.setLayout(null);
			String[] entetes = { "ID", "MATRICULE", "NOM", "PRENOM", "ADRESSE", "PSEUDO", "RESPONSABLE", "HOBBY",
					"DATE DE NAISSANCE", "DATE D'EMBAUCHE" };

			dt = new ActionsBDImpl();
			ArrayList<ProgrammeurBean> programmeurs = dt.getProgrammeurs();
			dt.fermerRessources();
			Object[][] donnees = new Object[programmeurs.size()][Constantes.NB_COLLONES];

			int i = 0;
			for (ProgrammeurBean prog : programmeurs) {
				donnees[i][0] = prog.getId();
				donnees[i][1] = prog.getMatricule();
				donnees[i][2] = prog.getNom();
				donnees[i][3] = prog.getPrenom();
				donnees[i][4] = prog.getAdresse();
				donnees[i][5] = prog.getPseudo();
				donnees[i][6] = prog.getResponsable();
				donnees[i][7] = prog.getHobby();
				donnees[i][8] = new SimpleDateFormat("yyyy-MM-dd").format(prog.getDateNaiss());
				donnees[i][9] = new SimpleDateFormat("yyyy-MM-dd").format(prog.getDateEmbauche());
				i++;
			}

			zoneAffichageProgrammeurs = new JTable(donnees, entetes);
			scroll = new JScrollPane(zoneAffichageProgrammeurs);
			size = scroll.getPreferredSize();
			scroll.setBounds(10, 25, size.width + 260, size.height - 200);
			nbProgrammeurs = new JLabel();
			String nbProgs = "Il y a " + i + " programmeur";
			if (i > 1) {
				nbProgs += "s";
			}
			nbProgrammeurs.setText(nbProgs);
			size = nbProgrammeurs.getPreferredSize();
			nbProgrammeurs.setBounds(20, 250, size.width, size.height);
			panelTous.add(nbProgrammeurs);
			panelTous.add(scroll);
			panelTous.add(retour);
			panelTous.add(titre);
			this.add(panelTous);
			zoneAffichageProgrammeurs.setEnabled(false);
			SwingUtilities.updateComponentTreeUI(this);
		}

		if (event.getSource() == menuModifier) {
			if (panelFormulaire != null) {
				remove(panelFormulaire);
			}
			if (panelTous != null) {
				remove(panelTous);
			}
			estAjouter = false;
			estSupprimer = false;
			estModifier = true;
			remove(panelFondEcran);
			formulaire();
			AfficherForm();
			reinitialise.setEnabled(false);
			valider.setEnabled(false);
			this.add(panelFormulaire);
			SwingUtilities.updateComponentTreeUI(this);
		}

		if (event.getSource() == menuAjouter) {
			if (panelFormulaire != null) {
				remove(panelFormulaire);
			}
			if (panelTous != null) {
				remove(panelTous);
			}
			remove(panelFondEcran);

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

		if (event.getSource() == menuSupprimer) {
			if (panelFormulaire != null) {
				remove(panelFormulaire);
			}
			if (panelTous != null) {
				remove(panelTous);
			}

			estAjouter = false;
			estSupprimer = true;
			estModifier = false;

			remove(panelFondEcran);
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
			dateNaissance.setEnabled(false);
			jSpinnerJourN.setEnabled(false);
			jSpinnerMoisN.setEnabled(false);
			jSpinnerAnN.setEnabled(false);
			jSpinnerJourE.setEnabled(false);
			jSpinnerMoisE.setEnabled(false);
			jSpinnerAnE.setEnabled(false);
			recherche.setEnabled(true);
			this.add(panelFormulaire);
			SwingUtilities.updateComponentTreeUI(this);
		}
		if (event.getSource() == actionQuitter) {
			int dialogResult = JOptionPane.showConfirmDialog(this, "Vérification", "Voulez-vous vraiment quitter ?",
					JOptionPane.YES_NO_OPTION);
			if (dialogResult == JOptionPane.YES_OPTION) {
				this.dispose();
			}
		}
		if (event.getSource() == actionInitBD) {
			int dialogResult = JOptionPane.showConfirmDialog(this, "Vérification", "Voulez-vous initialiser la BD ?",
					JOptionPane.YES_NO_OPTION);
			if (dialogResult == JOptionPane.YES_OPTION) {
				dt = new ActionsBDImpl();
				dt.initBD();
				dt.fermerRessources();
			}
		}
	}

	public void jButtonRecherhcerActionPerformed(java.awt.event.ActionEvent evt) {
		if (evt.getSource() == recherche) {
			dt = new ActionsBDImpl();
			try {
				progrBean = dt.getProgrammeur(this.jTMatricule.getText());// Une fois le programmeur trouver
				this.jTNom.setText(progrBean.getNom());// On prérempli chaque champs du formulaire avec ses
														// informations
				this.jTPrenom.setText(progrBean.getPrenom());
				this.jTAdresse.setText(progrBean.getAdresse());
				this.jTHobby.setText(progrBean.getHobby());
				this.jTPseudo.setText(progrBean.getPseudo());
				this.jTPseudo.setText(progrBean.getPseudo());
				this.jTResp.setText(progrBean.getResponsable());
				Calendar cal = Calendar.getInstance();
				cal.setTime(progrBean.getDateNaiss());
				this.jSpinnerJourN.setValue(cal.get(Calendar.DAY_OF_MONTH));
				this.jSpinnerMoisN.setValue(cal.get(Calendar.MONTH) + 1);
				this.jSpinnerAnN.setValue(cal.get(Calendar.YEAR));
				cal.setTime(progrBean.getDateEmbauche());
				this.jSpinnerJourE.setValue(cal.get(Calendar.DAY_OF_MONTH));
				this.jSpinnerMoisE.setValue(cal.get(Calendar.MONTH) + 1);
				this.jSpinnerAnE.setValue(cal.get(Calendar.YEAR));
				if (estModifier) {
					reinitialise.setEnabled(true);
					valider.setEnabled(true);
				} else if (estSupprimer) {
					reinitialise.setEnabled(false);
					valider.setEnabled(true);
				}
			} catch (ProgrammeurInconnuExeption e) {
				JOptionPane.showMessageDialog(this, e, "Echec", JOptionPane.ERROR_MESSAGE);
			}
			dt.fermerRessources();
		}
	}

	public void jButtonAnnulerActionPerformed(java.awt.event.ActionEvent evt) {
		if (evt.getSource() == annuler || evt.getSource() == retour) {
			if (panelFormulaire != null) {
				remove(panelFormulaire);
			}
			if (panelTous != null) {
				remove(panelTous);
			}
			accueil();
			SwingUtilities.updateComponentTreeUI(this);
		}
	}

	public void jButtonValiderActionPerformed(java.awt.event.ActionEvent evt) throws ParseException {
		if (evt.getSource() == valider) {
			dt = new ActionsBDImpl();
			if (estAjouter || estModifier) {// ci-dessous on recupere les données contenus dans les champs
				String matricule = jTMatricule.getText();
				String nom = jTNom.getText();
				String prenom = jTPrenom.getText();
				String responsable = jTResp.getText();
				String pseudo = jTPseudo.getText();
				String hobby = jTHobby.getText();
				String adresse = jTAdresse.getText();
				String dateDeNaissanceS = jSpinnerAnN.getValue().toString() + "-" + jSpinnerMoisN.getValue().toString()
						+ "-" + jSpinnerJourN.getValue().toString();
				String dateEmbaucheS = jSpinnerAnE.getValue().toString() + "-" + jSpinnerMoisE.getValue().toString()
						+ "-" + jSpinnerJourE.getValue().toString();
				java.sql.Date dateDeNaissance = java.sql.Date.valueOf(dateDeNaissanceS);
				java.sql.Date dateDeEmbauche = java.sql.Date.valueOf(dateEmbaucheS);
				if (estAjouter) {//comportement du boutton valider sur la page Ajouter
					dt.insertProgrammeur(matricule, nom, prenom, adresse, pseudo, responsable, hobby, dateDeNaissance,
							dateDeEmbauche);
					JOptionPane.showMessageDialog(this, "Le Programmeur est ajouté", "Succès!",
							JOptionPane.INFORMATION_MESSAGE);
					valider.setEnabled(false);
				} else if (estModifier) {//comportement du boutton valider sur la page Modifier
					dt.updateProgrammeur(matricule, nom, prenom, adresse, pseudo, responsable, hobby, dateDeNaissance,
							dateDeEmbauche);
					JOptionPane.showMessageDialog(this, "Le Programmeur est modifié", "Succès!",
							JOptionPane.INFORMATION_MESSAGE);
					valider.setEnabled(false);
					reinitialise.setEnabled(false);
				}
				viderChamps();
			} else if (estSupprimer) {//comportement du boutton valider sur la page Supprimer
				String matricule = jTMatricule.getText();
				dt.deleteProgrammeur(matricule);
				JOptionPane.showMessageDialog(this, "Le Programmeur est supprimé", "Succès!",
						JOptionPane.INFORMATION_MESSAGE);
				viderChamps();
				valider.setEnabled(false);
			}
		}
		dt.fermerRessources();
	}

	public void jButtonReinitialiserActionPerformed(java.awt.event.ActionEvent evt) {
		if (evt.getSource() == reinitialise) {
			viderChamps();
			if (estModifier) {
				reinitialise.setEnabled(false);
				valider.setEnabled(false);
			}
		}
	}

	/**
	 * Vide les champs du formulaire d'ajout/modification
	 */
	public void viderChamps() {
		jTMatricule.setText("");
		jTNom.setText("");
		jTPrenom.setText("");
		jTResp.setText("");
		jTPseudo.setText("");
		jTHobby.setText("");
		jTAdresse.setText("");
		jSpinnerJourN.setValue(1);
		jSpinnerMoisN.setValue(1);
		jSpinnerAnN.setValue(1945);
		jSpinnerJourE.setValue(1);
		jSpinnerMoisE.setValue(1);
		jSpinnerAnE.setValue(1945);
	}
}
