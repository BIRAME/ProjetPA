package modele;

import java.io.File;
import java.io.FileFilter;
import java.io.Serializable;
import java.util.ArrayList;
import sauvegarde.SauvegardeEtChargement;

public class GestionnaireDeFichiers implements Serializable{
    
    private transient File fichierSauvegarde;
    private File fileActuel;
    private ArrayList<File> listeFileEnAvant;

   public GestionnaireDeFichiers(File fichierSauvegarde) {
        this.fichierSauvegarde = fichierSauvegarde;
        if (this.fichierSauvegarde != null && this.fichierSauvegarde.length() != 0) {
            GestionnaireDeFichiers tmpGdm = SauvegardeEtChargement.chargerUnFichier(this.fichierSauvegarde);
            this.fileActuel = tmpGdm.fileActuel;
            this.listeFileEnAvant = tmpGdm.listeFileEnAvant;
        } 
        else {
            this.fileActuel = null;
            this.listeFileEnAvant = new ArrayList<File>();
        }
    }
    
    public void setFileActuel(File f) {
        if (f.isDirectory()){
            this.fileActuel = f;
            SauvegardeEtChargement.sauvegardeObjet(this, fichierSauvegarde);
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
            return fileActuel.listFiles(new FileFilter() {
                public boolean accept(File pathname) {
                    return pathname.canExecute();
                    }
            });
        }
    }
    
    public void retourEnArriere() {
        if (this.fileActuel != null) {
            this.listeFileEnAvant.add(this.fileActuel);
            this.fileActuel = this.fileActuel.getParentFile();
            SauvegardeEtChargement.sauvegardeObjet(this, fichierSauvegarde);
        }
    }
    
    public void retourEnAvant() {
        if (!this.listeFileEnAvant.isEmpty()) {
            this.fileActuel = this.listeFileEnAvant.remove(this.listeFileEnAvant.size()-1);
            SauvegardeEtChargement.sauvegardeObjet(this, fichierSauvegarde);
        }
    }
}
