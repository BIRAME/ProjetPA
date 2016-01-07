package plugins;

/**
 * Classe qui represente l'implementation de la fabrique de plugins implements
 * PluginFactory, interface de la fabrique de plugins
 * 
 */
public class PluginFactoryImpl implements PluginFactory {

	public PluginFactoryImpl() {
		createPluginAnalyse();
	}

	/**
	 * Methode qui creer un plugin d'analyse qui consiste a afficher une fenetre
	 * qui indique plusieurs statistique du repertoire courant, sur les
	 * specificites des fichiers contenus
	 *
	 */
	@Override
	public PluginAnalyse createPluginAnalyse() {
		return new PluginAnalyseImpl();
	}

}
