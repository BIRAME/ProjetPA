package sauvegarde;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SauvegardeEtChargement {
    // méthode qui permet de sauvegarder n'importe quel objet serializable
    public static void sauvegardeObjet(Object objet, File fileSave) {
        ObjectOutputStream e1;
        try {
            e1 = new ObjectOutputStream(new FileOutputStream(fileSave));
            e1.writeObject(objet);
            e1.flush();
            e1.close();
        } catch (IOException ex) {
            Logger.getLogger(SauvegardeEtChargement.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    // méthode qui permet de charger n'importe quel objet contenu dans le fichier
    public static <T> T chargerUnFichier(File file) {
        Object res = null;
        ObjectInputStream obj = null;
        try {
            obj = new ObjectInputStream(new FileInputStream(file));
            res = obj.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SauvegardeEtChargement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SauvegardeEtChargement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SauvegardeEtChargement.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return (T) res;
    }
}
