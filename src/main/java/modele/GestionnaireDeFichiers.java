package modele;

import java.io.File;
import java.util.ArrayList;

public class GestionnaireDeFichiers {
	
    private File fileActuel;
    private ArrayList<File> listeFileEnAvant;

    public void GestionnaireDeFichiers() {
        this.fileActuel = null;
        this.listeFileEnAvant = new ArrayList<File>();
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
        if (this.fileActuel != null) {System.out.println("test : " + this.listeFileEnAvant);
            this.listeFileEnAvant.add(this.fileActuel);
            this.fileActuel = this.fileActuel.getParentFile();
        }
    }
    
    public void retourEnAvant() {
        if (!this.listeFileEnAvant.isEmpty()) {
            this.fileActuel = this.listeFileEnAvant.remove(this.listeFileEnAvant.size());
        }
    }
}
