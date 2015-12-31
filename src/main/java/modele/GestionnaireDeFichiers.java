package modele;

import java.io.File;

public class GestionnaireDeFichiers {
	
    private File fileActuel;

    public void GestionnaireDeFichiers() {
        this.fileActuel = null;
    }
    
    public void setFileActuel(File f) {
        if (f.isDirectory()){
            this.fileActuel = f;
        }
    }
    
    public File getFileActuel() {
        return this.fileActuel;
    }

    public File[] listeFiles() {
        // cas du démarrage du programme, on démarre sur la racine indépendamment du système
        if (fileActuel == null) {
            return File.listRoots();
        }
        else {
            return fileActuel.listFiles();
        }
    }
    
    public void retourEnArriere() {
        if (this.fileActuel != null) {
            this.fileActuel = this.fileActuel.getParentFile();
        }
    }
	
}
