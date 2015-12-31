package ihm;

import modele.GestionnaireDeFichiers;

public class Main {

	public static void main(String[] args) {
                GestionnaireDeFichiers gdf = new GestionnaireDeFichiers();
		Explorer exp = new Explorer(gdf);
	}
}
