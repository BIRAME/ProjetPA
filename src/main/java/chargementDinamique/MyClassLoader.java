package chargementDinamique;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.SecureClassLoader;
import java.util.ArrayList;



public class MyClassLoader extends SecureClassLoader
{

	public ArrayList<File> path = new ArrayList<File>();

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException
	{
		byte[] b = null;
		try
		{
			b = loadClassData(name);
			
		} catch (IOException e)
		{
			
			e.printStackTrace();
		}
		return super.defineClass(name, b, 0, b.length);
	}

	private byte[] loadClassData(String name) throws ClassNotFoundException, IOException
	{

		
		String chemin = "";
		
			chemin = name.replaceAll("\\.", "\\\\");
	
		for (File f : path)
		{


			 if (f.isDirectory())
			{
				String fullchemin = chemin.concat(".class");
				File[] listFiles = f.listFiles();
				for (int i = 0; i < listFiles.length; i++)
				{
					String n = listFiles[i].getPath();
					
					File file = new File(n);
					if (n.contains(fullchemin))
					{
						if (file.exists())
						{
							System.out.println("trouvé");
							byte[] b = Files.readAllBytes(file.toPath());
							if (b != null)
								return b;
						}
					}
				}
			}
		}
		return null;
	}
}