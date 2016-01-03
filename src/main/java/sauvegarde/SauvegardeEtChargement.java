package sauvegarde;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import modele.GestionnaireDeFichiers;

public class SauvegardeEtChargement {
    
    public static void sauvegardeGestionnaireDeFichiers(GestionnaireDeFichiers gdf, File fileSave) {
        ObjectOutputStream e1;
        try {
            e1 = new ObjectOutputStream(new FileOutputStream(fileSave));
            e1.writeObject(gdf);
            e1.flush();
            e1.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static GestionnaireDeFichiers chargerUnFichier(File file) {
        Object res = null;
        ObjectInputStream obj = null;
        try {
            obj = new ObjectInputStream(new FileInputStream(file));
            res = obj.readObject();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return (GestionnaireDeFichiers) res;
    }
}
