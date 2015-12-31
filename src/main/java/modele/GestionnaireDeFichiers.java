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
        // cas du d�marrage du programme, on d�marre sur la racine ind�pendamment du syst�me
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
