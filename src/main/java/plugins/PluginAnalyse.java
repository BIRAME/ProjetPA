package plugins;

import javax.swing.JDialog;

/**
 * Interface du plugin d'analyse
 * @extends PluginBase
 */
public interface PluginAnalyse extends PluginBase {
	
	public JDialog analyseCurrentFolder(String currentFolder);
}
