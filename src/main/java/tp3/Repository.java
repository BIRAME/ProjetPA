/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Repository {
    
    public File base;
    
    public Repository(File base) {  
        this.base = base;
    }  
  
    public List<Class<?>> load() { 
        List<Class<?>> listeClass = new ArrayList<Class<?>>() {};
        ArrayList<File> path = new ArrayList<File>();
        path.add(base);
        MyClassLoader mcl = new MyClassLoader(path);
        File[] liste = base.listFiles();
        StringBuilder prefixe = new StringBuilder();
        loadRecursive(listeClass,mcl,liste,prefixe);
        return listeClass;
    }
    
    private void loadRecursive(List<Class<?>> listeClass,MyClassLoader mcl,File[] liste,StringBuilder prefixe) {
        for (int i = 0; i < liste.length; i++) {
            String[] fichier = liste[i].getName().split("\\.");
            // si le file est un dossier, on retient le nom du package et on lance la récursivité
            if (liste[i].isDirectory()) {
                StringBuilder prefixeSuivant = new StringBuilder(prefixe);
                prefixeSuivant.append(liste[i].getName()).append(".");
                loadRecursive(listeClass, mcl,liste[i].listFiles(),prefixeSuivant);
            }
            // si c'est un zip ou jar on lance le chargement de toutes les classes présentes
            else if (fichier[fichier.length-1].equals("zip") || fichier[fichier.length-1].equals("jar")) {
                try {
                    ZipFile zip = new ZipFile(liste[i]);
                    Enumeration<? extends ZipEntry> zipListe = zip.entries();
                    while (zipListe.hasMoreElements()) {
                        String nom = zipListe.nextElement().getName();
                        String[] nomSplit = nom.split("\\.");
                        if (nomSplit.length > 1 && nomSplit[nomSplit.length-1].equals("class")) {
                            try {
                                listeClass.add(mcl.loadClass(nomSplit[0].replace("/", ".")));
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }catch (IOException ex) {
                    Logger.getLogger(MyClassLoader.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            // on charge tous les files qui sont des classes
            else {
            String[] nomFichier = liste[i].getName().split("\\.");
                if (nomFichier[nomFichier.length-1].equals("class")) {
                    try {
                        listeClass.add(mcl.loadClass(prefixe.toString().concat(nomFichier[0])));
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
}
