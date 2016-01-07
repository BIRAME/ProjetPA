package plugins;

import java.awt.Color;
import java.awt.Font;
import java.io.File;

import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 * Classe qui represente les fonctionnalités du plugin d'analyse
 * implements PluginAnalyse, interface du plugin d'analyse 
 * 
 */
public class PluginAnalyseImpl implements PluginAnalyse {

	/**
	 * Methode qui donne plusieurs statistique du dossier courant
	 * @attr String currentFolder
	 * 
	 * Nombre de repertoires
	 * Nombre de fichiers excels
	 * Nombre de fichiers PDF
	 * Nombre de fichiers Word
	 * 
	 */
	@Override
	public JDialog analyseCurrentFolder(String currentFolder) {
		System.out.println("Analyse Plugin");
		
		JDialog fenetrePopUp = new JDialog();

		File dossierCourant = new File(currentFolder);
		Long taille = dossierCourant.getTotalSpace();
		System.out.println(taille);
		int compteurDossier = 0;
		int compteurExcel = 0;
		int compteurPDF = 0;
		int compteurWord = 0;
		int compteurAutreFichiers = 0;

		String[] listefichiers;
		listefichiers = dossierCourant.list();
		for (int i = 0; i < listefichiers.length; i++) {

			File fichierTrouve = new File(currentFolder + File.separator + listefichiers[i]);

			if (fichierTrouve.isDirectory()) {
				compteurDossier++;

			} else if (listefichiers[i].endsWith(".xls") == true || listefichiers[i].endsWith(".xlsx") == true) {
				compteurExcel++;

			} else if (listefichiers[i].endsWith(".pdf") == true) {
				compteurPDF++;

			} else if (listefichiers[i].endsWith(".docx") == true) {
				compteurWord++;

			} else {
				compteurAutreFichiers++;
			}
		}

		JLabel zoneTexte = new JLabel();
		zoneTexte.setText("<html>Répertoire Courant : " + currentFolder + "<br>" + "Taille Répertoire : " + taille + " bytes <br>" + "Nombre de dossiers : " + compteurDossier + " <br>" + "Nombre de PDF : " + compteurPDF + " <br>" + "Nombre de Excel : " + compteurExcel + " <br>" + "Nombre de Word : " + compteurWord + " <br>" + "Nombre d'autres fichiers : " + compteurAutreFichiers + " <br></html>");

		Font font = new Font("Calibri", Font.CENTER_BASELINE, 14);
		zoneTexte.setFont(font);
		zoneTexte.setBackground(Color.LIGHT_GRAY);

		fenetrePopUp.add(zoneTexte);

		fenetrePopUp.setSize(300, 175);
		fenetrePopUp.setTitle("Contenu du répertoire courant");
		fenetrePopUp.setVisible(true);
		fenetrePopUp.setModal(true);

		return fenetrePopUp;
	}

	
	@Override
	public String getLibelle() {
		// TODO Auto-generated method stub
		return "Analyse répertoire courant";
	}

	@Override
	public int getCategorie() {
		// TODO Auto-generated method stub
		return 1;
	}

	
}
