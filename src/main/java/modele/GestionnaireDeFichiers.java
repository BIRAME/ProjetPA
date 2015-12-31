package modele;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class GestionnaireDeFichiers implements Serializable{
	
    private File fileActuel;
    private ArrayList<File> listeFileEnAvant;

    public GestionnaireDeFichiers() {
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
        // cas du d�marrage du programme, on d�marre sur la racine ind�pendamment du syst�me
        if (fileActuel == null) {
            return File.listRoots();
        }
        else {
            /*File[] listeFile
            for (int i = 0; i < file{
                
                tmp.
            }*/
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
            this.fileActuel = this.listeFileEnAvant.remove(this.listeFileEnAvant.size()-1);
        }
    }
}
