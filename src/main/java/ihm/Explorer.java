package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;

import modele.GestionnaireDeFichiers;
import plugins.PluginAnalyseImpl;
import plugins.PluginVueImpl;
import tree.FileTree;

public class Explorer extends JFrame {

	private static final long serialVersionUID = 1L;

	private Container panel = getContentPane();

	private JButton btnSuivant = new JButton(">");
	private JButton btnPrecedent = new JButton("<");

	private JMenuBar menu;
	private JMenu menuFichier;
	private JMenu menuPluginVue;
	private JMenu menuPluginAnalyse;

	private JMenuItem menuItemPluginCharge;
	private JMenuItem menuItemPluginLancer;
	private JMenuItem menuItemPluginAnalyse;
	private JMenuItem menuItemPluginVue;

	private JPanel topPanel = new JPanel();
	private JPanel panelPrincipal = new JPanel(new BorderLayout());
	private JPanel zoneOutils = new JPanel(new FlowLayout((int) LEFT_ALIGNMENT));
	private JPanel arbreNavigation = new JPanel(new BorderLayout());
	private JPanel premiereLigne;

	JSplitPane splitPane;

	private GestionnaireDeFichiers gestionnaireDeFichiers;
	private JScrollPane jspListeFile;
	private JList<File> listeFile;
	
	private JTree arbreFichier = new FileTree("/").getFileTree();
	private JScrollPane jsTreeFile;

	public Explorer(GestionnaireDeFichiers gdf) {

		this.gestionnaireDeFichiers = gdf;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Explorateur de fichier");
		this.setSize(800, 600);
		this.setBackground(Color.GREEN);

		topPanel.setLayout(new BorderLayout());
		panel.add(topPanel);

		this.createMenu();
		this.afficherOutils();

		premiereLigne = new JPanel(new BorderLayout());
		premiereLigne.add(zoneOutils, BorderLayout.WEST);
		topPanel.add(premiereLigne, BorderLayout.NORTH);
		arbreNavigation.add(arbreFichier);
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, arbreNavigation, panelPrincipal);
		splitPane.setDividerLocation(150);
		topPanel.add(splitPane);

		this.listeFile = new JList<File>(this.gestionnaireDeFichiers.listeFiles());
		this.listeFile.addMouseListener(new MouseEventListe());
		this.jspListeFile = new JScrollPane(this.listeFile);
		this.jsTreeFile = new JScrollPane(arbreFichier);
		this.arbreNavigation.add(this.jsTreeFile);
		this.panelPrincipal.add(this.jspListeFile);
		this.setVisible(true);

	}

	private void createMenu() {
		this.menu = new JMenuBar();
		this.menuFichier = new JMenu();
		this.menuPluginVue = new JMenu();
		this.menuItemPluginCharge = new JMenuItem();
		this.menuItemPluginLancer = new JMenuItem();
		this.menuPluginAnalyse = new JMenu();
		this.menuItemPluginAnalyse = new JMenuItem();
		this.menuItemPluginVue = new JMenuItem();

		this.menu.add(this.menuFichier);
		this.menu.add(this.menuPluginVue);
		this.menu.add(this.menuPluginAnalyse);
		this.menu.setBackground(Color.gray);

		this.menuFichier.setText("Fichier");
		this.menuFichier.add(this.menuItemPluginCharge);
		this.menuFichier.add(this.menuItemPluginLancer);
		this.menuFichier.addSeparator();

		this.menuPluginVue.setText("Plugins de vue");
		this.menuPluginVue.add(this.menuItemPluginVue);
		this.menuItemPluginVue.addActionListener(new VuePlugin());

		this.menuPluginAnalyse.setText("Plugins d'analyse");
		this.menuPluginAnalyse.add(this.menuItemPluginAnalyse);
		this.menuItemPluginAnalyse.addActionListener(new AnaylsePlugin());

		this.menuItemPluginCharge.setText("Charger un plugin");
		this.menuItemPluginCharge.addActionListener(new ChargerPlugins());

		this.menuItemPluginLancer.setText("Lancer les plugins chargés");

		this.menuItemPluginAnalyse.setText("Statistiques");
		
		this.menuItemPluginVue.setText("Changer couleur");		

		this.setJMenuBar(this.menu);
	}

	private void afficherOutils() {

		zoneOutils.add(btnPrecedent);
		zoneOutils.add(btnSuivant);

		btnPrecedent.addActionListener(new ActionPrecedent());
		btnSuivant.addActionListener(new ActionEnAvant());
	}

	class ActionPrecedent implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			System.out.println("Action effectuée par ActionPrecedent");
			gestionnaireDeFichiers.retourEnArriere();
			listeFile.setListData(gestionnaireDeFichiers.listeFiles());
		}

	}

	class ActionEnAvant implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// System.out.println("Action effectuée par ActionEnAvant");
			gestionnaireDeFichiers.retourEnAvant();
			listeFile.setListData(gestionnaireDeFichiers.listeFiles());
		}

	}

	class AnaylsePlugin implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println(gestionnaireDeFichiers.getFileActuel());
			PluginAnalyseImpl pai = new PluginAnalyseImpl();
			pai.analyseCurrentFolder(gestionnaireDeFichiers.getFileActuel().getPath());
		}

	}
	
	class VuePlugin implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			System.out.println("Changer de couleur");
			PluginVueImpl pvi = new PluginVueImpl();
			pvi.changeColor(panelPrincipal, zoneOutils, premiereLigne, arbreFichier, menu, jsTreeFile, menuFichier, menuPluginVue, menuPluginAnalyse);
		}
		
	}

	class MouseEventListe implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {
				// System.out.println("Action effectuée par MouseEventListe");
				gestionnaireDeFichiers.setFileActuel(listeFile
						.getSelectedValue());
				listeFile.setListData(gestionnaireDeFichiers.listeFiles());
			}
		}

		public void mousePressed(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

	}

	class ChargerPlugins implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JFileChooser dialogue = new JFileChooser(new File("./src/"));
			PrintWriter sortie = null;
			File fichier;

			if (dialogue.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				fichier = dialogue.getSelectedFile();
				try {
					sortie = new PrintWriter(new FileWriter(fichier.getPath(), true));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				sortie.close();
			}

		}
	}
}
