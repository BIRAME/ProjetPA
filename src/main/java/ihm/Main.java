package ihm;

import java.io.File;
import modele.GestionnaireDeFichiers;

public class Main {

    /*Mettre le chemin vers le fichier*/
    static File fichierSave = new File(System.getProperty("user.dir") + "/saveGdp.gdm");

    public static void main(String[] args) {
        System.out.println("ihm.Main.main() :" + fichierSave.getPath());
        GestionnaireDeFichiers gdf = new GestionnaireDeFichiers(fichierSave);
        Explorer exp = new Explorer(gdf);
    }
}
