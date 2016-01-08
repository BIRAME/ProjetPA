package plugins;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;

public interface PluginVue extends PluginBase {

	public void changeColor(JPanel panelPrincipal, JPanel zoneOutils, JPanel premiereLigne,
			JTree arbreFichier, JMenuBar menu, JScrollPane jsTreeFile,
			JMenu menuFichier, JMenu menuPluginVue, JMenu menuPluginAnalyse);
}
