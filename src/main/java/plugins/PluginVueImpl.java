package plugins;

import java.awt.Color;

import javax.swing.JColorChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

public class PluginVueImpl extends DefaultTreeCellRenderer implements PluginVue {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Color couleur;

	@Override
	public String getLibelle() {
		// TODO Auto-generated method stub
		return "Changer de couleur de theme";
	}

	@Override
	public int getCategorie() {
		// TODO Auto-generated method stub
		return 2;
	}


	@Override
	public void changeColor(JPanel panelPrincipal, JPanel zoneOutils, JPanel premiereLigne,
			JTree arbreFichier, JMenuBar menu, JScrollPane jsTreeFile,
			JMenu menuFichier, JMenu menuPluginVue, JMenu menuPluginAnalyse) {
        Object[] buttons = new String[]{"Menu", "Navigation", "Arbre", "Texte Menu"};
        int retour = JOptionPane.showOptionDialog(this, "Que voulez vous changer ?", "Plugin Vue", 0, 1, null, buttons, buttons[0]);
        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer)arbreFichier.getCellRenderer();
        this.couleur = JColorChooser.showDialog(null, "couleur de fond", Color.WHITE);
        switch (retour) {
            case 0: {
                menu.setBackground(this.couleur);
                if (this.couleur.getBlue() < 154 && this.couleur.getRed() < 154 && this.couleur.getGreen() < 154) {
                    menuFichier.setForeground(Color.white);
                    menuPluginVue.setForeground(Color.white);
                    menuPluginAnalyse.setForeground(Color.white);
                    break;
                }
                menuFichier.setForeground(Color.black);
                menuPluginVue.setForeground(Color.black);
                menuPluginAnalyse.setForeground(Color.black);
                break;
            }
            case 1: {
                zoneOutils.setBackground(this.couleur);
                premiereLigne.setBackground(this.couleur);
                break;
            }
            case 2: {
                arbreFichier.setBackground(this.couleur);
                //renderer.setBackgroundNonSelectionColor(this.couleur);
                //jsTreeFile.setBackground(this.couleur);
                if (this.couleur.getBlue() < 154 && this.couleur.getRed() < 154 && this.couleur.getGreen() < 154) {
                    renderer.setTextNonSelectionColor(Color.white);
                    renderer.setTextSelectionColor(Color.black);
                    renderer.setBackgroundSelectionColor(Color.white);
                    break;
                } else if (this.couleur.getBlue() > 154 && this.couleur.getRed() > 154 && this.couleur.getGreen() > 154) {
                	renderer.setTextNonSelectionColor(Color.black);
                    renderer.setTextSelectionColor(Color.white);
                    renderer.setBackgroundSelectionColor(Color.black);
                    break;
                }
                renderer.setTextNonSelectionColor(Color.black);
                renderer.setTextSelectionColor(Color.white);
                renderer.setBackgroundSelectionColor(Color.black);
                break;
            }
            case 3: {
            	panelPrincipal.setForeground(this.couleur);
                zoneOutils.setForeground(this.couleur);
                premiereLigne.setForeground(this.couleur);
                arbreFichier.setForeground(this.couleur);
                menu.setForeground(this.couleur);
                jsTreeFile.setForeground(this.couleur);
                menuFichier.setForeground(this.couleur);
                menuPluginVue.setForeground(this.couleur);
                menuPluginAnalyse.setForeground(this.couleur);
                if (this.couleur.getBlue() < 154 && this.couleur.getRed() < 154 && this.couleur.getGreen() < 154) {
                	menu.setBackground(Color.white);
                	menuFichier.setBackground(Color.white);
                    menuPluginVue.setBackground(Color.white);
                    menuPluginAnalyse.setBackground(Color.white);
                    break;
                } else if (this.couleur.getBlue() > 154 && this.couleur.getRed() > 154 && this.couleur.getGreen() > 154) {
                	menu.setBackground(Color.black);
                	menuFichier.setBackground(Color.black);
                    menuPluginVue.setBackground(Color.black);
                    menuPluginAnalyse.setBackground(Color.black);
                    break;
                }
                break;
            }
        }
		
	}
}
