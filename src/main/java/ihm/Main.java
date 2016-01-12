package ihm;

import java.io.File;
import modele.GestionnaireDeFichiers;

public class Main {

    // mettre le chemin vers les fichiers
    static File fichierSave = new File(System.getProperty("user.dir") + "/saveGdf.gdm");
    static File fichierSave2 = new File(System.getProperty("user.dir") + "/saveExp.gdm");
    
    public static void main(String[] args) {
        System.out.println("ihm.Main.main() :" + fichierSave.getPath());
        GestionnaireDeFichiers gdf = new GestionnaireDeFichiers(fichierSave);
        Explorer exp = new Explorer(gdf,fichierSave2);
    }
}
