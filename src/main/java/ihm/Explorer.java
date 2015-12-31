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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.ListModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import modele.GestionnaireDeFichiers;

public class Explorer extends JFrame {

    private static final long serialVersionUID = 1L;

    private Container panel = getContentPane();

    ImageIcon imgPrec = new ImageIcon("flechePrecedent.png");
    ImageIcon imgSuiv = new ImageIcon("flecheSuivant.png");
    private JButton btnSuivant = new JButton(imgSuiv);
    private JButton btnPrecedent = new JButton(imgPrec);

    private JMenuBar menu;
    private JMenu menuFichier;
    private JMenu menuPluginVue;
    private JMenu menuDesactivePlugin;
    private JMenu menuPluginAnalyse;

    private JMenuItem menuItemOuvrir;
    private JMenuItem menuItemPluginCharge;
    private JMenuItem menuItemPluginLancer;
    private JMenuItem menuItemDesacPluginAnalyse;
    private JMenuItem menuItemDesacPluginVue;

    private JPanel topPanel = new JPanel();
    private JPanel panelPrincipal = new JPanel(new BorderLayout());
    private JPanel zoneOutils = new JPanel(new FlowLayout((int) LEFT_ALIGNMENT));
    private JPanel arbreNavigation = new JPanel(new BorderLayout());
    private JPanel premiereLigne;
    JSplitPane splitPane;

    private JTree myTree;
    private DefaultTreeModel myModel;
    private DefaultMutableTreeNode treeNodePrincipal;

    private JScrollPane jscpJTree = new JScrollPane();
    
    private GestionnaireDeFichiers gestionnaireDeFichiers;
    private JScrollPane jspListeFile;
    private JList<File> listeFile;

    public Explorer(GestionnaireDeFichiers gdf) {

        this.gestionnaireDeFichiers = gdf;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Explorateur de fichier");
        this.setSize(800, 600);
        this.setBackground(Color.GREEN);

        topPanel.setLayout(new BorderLayout());
        panel.add(topPanel);

        this.createMenu();
        this.afficherArbreNavigation();
        this.afficherOutils();

        premiereLigne = new JPanel(new BorderLayout());
        premiereLigne.add(zoneOutils, BorderLayout.WEST);
        topPanel.add(premiereLigne, BorderLayout.NORTH);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, arbreNavigation, panelPrincipal);
        splitPane.setDividerLocation(150);
        topPanel.add(splitPane);
        
        this.listeFile = new JList<File>(this.gestionnaireDeFichiers.listeFiles());
        this.listeFile.addMouseListener(new MouseEventListe());
        this.jspListeFile = new JScrollPane(this.listeFile);
        this.panelPrincipal.add(this.jspListeFile);
        
        this.setVisible(true);

    }

    private void createMenu() {
        this.menu = new JMenuBar();
        this.menuFichier = new JMenu();
        this.menuPluginVue = new JMenu();
        this.menuItemOuvrir = new JMenuItem();
        this.menuItemPluginCharge = new JMenuItem();
        this.menuItemPluginLancer = new JMenuItem();
        this.menuDesactivePlugin = new JMenu();
        this.menuItemDesacPluginAnalyse = new JMenuItem();
        this.menuItemDesacPluginVue = new JMenuItem();
        this.menuPluginAnalyse = new JMenu();

        // menuBar
        this.menu.add(this.menuFichier);
        this.menu.add(this.menuPluginVue);
        this.menu.add(this.menuDesactivePlugin);
        this.menu.add(this.menuPluginAnalyse);
        this.menu.setBackground(Color.gray);

        // fileMenu
        this.menuFichier.setText("Fichier");
        this.menuFichier.add(this.menuItemOuvrir);
        this.menuFichier.add(this.menuItemPluginCharge);
        this.menuFichier.add(this.menuItemPluginLancer);
        this.menuFichier.addSeparator();

        // viewPluginsMenu
        this.menuPluginVue.setText("Plugins de vue");

        // viewPluginsMenu
        this.menuPluginAnalyse.setText("Plugins d'analyse");

        // desactivatePlugin
        this.menuDesactivePlugin.setText("Désactiver des plugins");
        this.menuDesactivePlugin.add(this.menuItemDesacPluginAnalyse);
        this.menuDesactivePlugin.add(this.menuItemDesacPluginVue);

        // ouvrirMenuItem
        this.menuItemOuvrir.setText("Ouvrir");

        // loadMenuItem
        this.menuItemPluginCharge.setText("Charger un plugin");

        // runPluginsMenuItem
        this.menuItemPluginLancer.setText("Lancer les plugins chargés");

        // desactivateTablePluginItem
        this.menuItemDesacPluginAnalyse.setText("Plugin d'analyse");

        // Item
        this.menuItemDesacPluginVue.setText("Plugin de vue");

        this.setJMenuBar(this.menu);
    }

    private void afficherArbreNavigation() {

        treeNodePrincipal = new DefaultMutableTreeNode();

        // Construction du modele de l'arbre.
        myModel = new DefaultTreeModel(treeNodePrincipal);

        // Construction de l'arbre.
        myTree = new JTree(myModel);

        // on rend invis
        myTree.setRootVisible(false);

        jscpJTree.setViewportView(myTree);

        arbreNavigation.add(jscpJTree);
    }

    private void afficherOutils() {

        //btnPrecedent.setPreferredSize(new Dimension(40, 40));
        zoneOutils.add(btnPrecedent);

        //btnSuivant.setPreferredSize(new Dimension(40, 40));
        zoneOutils.add(btnSuivant);

        //btnPrecedent.setActionCommand("ancienRep");
        //btnPrecedent.setEnabled(false);
        btnPrecedent.addActionListener(new ActionPrecedent());
        
        //btnSuivant.setActionCommand("nextFolder");
        //btnSuivant.setEnabled(false);
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
            System.out.println("Action effectuée par ActionEnAvant");
            gestionnaireDeFichiers.retourEnAvant();
            listeFile.setListData(gestionnaireDeFichiers.listeFiles());
        }

    }
    
    class MouseEventListe implements MouseListener {

        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {
                System.out.println("Action effectuée par MouseEventListe");
                gestionnaireDeFichiers.setFileActuel(listeFile.getSelectedValue());
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
}
