package modele;

import java.io.File;

public class GestionnaireDeFichiers {
	
	private File[] roots;
	
	public void GestionnaireDeFichiers() {
		this.roots = File.listRoots();
	}
	
	public File[] listeFile(File f) {
		if (f.isDirectory()){
			return f.listFiles();
		}
		return null;
	}
	
	
}
