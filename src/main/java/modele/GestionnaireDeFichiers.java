package modele;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

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

    public ArrayList<File> ListeFiles() {
        // cas du d�marrage du programme, on d�marre sur la racine ind�pendamment du syst�me
        if (fileActuel == null) {
            return new ArrayList<File>(Arrays.asList(File.listRoots()));
        }
        else {
            return new ArrayList<File>(Arrays.asList(fileActuel.listFiles()));
        }
    }
    
    public void retourEnArriere() {
        this.fileActuel = this.fileActuel.getParentFile();
    }
	
}
