/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureClassLoader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class MyClassLoader extends SecureClassLoader{
    
    private ArrayList<File> path = new ArrayList<File>();  
    
    public MyClassLoader(ArrayList<File> path) {
        this.path = path;
    }
    
    
    @Override  
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] b = loadClassData(name);  
        return super.defineClass(name, b, 0, b.length);  
    }  
  
    private byte[] loadClassData(String name) throws ClassNotFoundException {
            name = name.replace(".", "/");
            name = "/" + name + ".class";
            
            for (int i = 0; i < path.size(); i++) {
                byte[] resultat = null;
                String name2 = name.substring(1);
                //on cherche d'abord si la classe est dans un zip ou jar
                resultat = zipJar(i, name2);
                if (resultat != null) {
                    return resultat;
                }
                // puis on cherche directement le fichier .class
                resultat = facile(i, name);
                if (resultat != null) {
                    return resultat;
                }
            }
        return null;
    }  

    public byte[] zipJar(int i, String name) {
        //System.out.println("name : " + name);
        String[] listeFichiers = this.path.get(i).list();
        for (int j = 0; j < listeFichiers.length; j++) {
            //System.out.println("Fichiers " + j + " : " + listeFichiers[j]);
            String[] fichier = listeFichiers[j].split("\\.");
            if (fichier[fichier.length-1].equals("zip") || fichier[fichier.length-1].equals("jar")) {
                try {
                    ZipFile zip = new ZipFile(path.get(i).getAbsolutePath() + "\\" + listeFichiers[j]);
                    ZipEntry zipEntry = zip.getEntry(name);
                    if (zipEntry != null) {
                        int aLire = (int)zipEntry.getSize();
                        byte[] b = new byte[aLire];
                        int lu = zip.getInputStream(zipEntry).read(b,0,aLire);
                        while (lu < aLire) {
                            lu += zip.getInputStream(zipEntry).read(b, 0, aLire-lu);
                        }
                        return b;
                    }
                }catch (IOException ex) {
                    Logger.getLogger(MyClassLoader.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    public byte[] facile(int i, String name) {
        
        try {
            String chemin = "file:///"+path.get(i).getAbsolutePath().concat(name).replace("\\", "/").replace(" ","%20");
            System.out.println(chemin);
            return Files.readAllBytes(Paths.get(new URI(chemin)));
        } catch (URISyntaxException ex) {
            Logger.getLogger(MyClassLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MyClassLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }  
}
