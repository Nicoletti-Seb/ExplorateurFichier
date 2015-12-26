package fr.miage.m1.pa.explorateur.controleur.classloader;


import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureClassLoader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ExplorateurClassLoader extends SecureClassLoader {
	
	private List<File> listPath = new ArrayList<File>();
	
	public ExplorateurClassLoader(List<File> listPath) {
		this.listPath = listPath;
	}


	@Override protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] b = loadClassData(name);
		return super.defineClass(name, b, 0, b.length);
	}

	private byte[] loadClassData(String name) throws ClassNotFoundException {
		String namePackage =  name.replace(".", "/");
		
		Iterator<File> i = listPath.iterator();
		while( i.hasNext() ){
			File f = i.next();
			
			byte [] data = null;
			if( f.isDirectory() ){
				data = loadClassDataWithFolder(namePackage, f);
			}
			if( f.getName().endsWith(".zip") ){
				try {
					data = loadClassDataWithZip(namePackage, new ZipFile(f));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else if( f.getName().endsWith(".jar")){
				try {
					data = loadClassDataWithZip(namePackage, new JarFile(f));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if( data == null ){
				continue;
			}else{
				return data;
			}
			
		}
		
		//not found
		return null;
	}
	
	private byte[] loadClassDataWithFolder(String namePackage, File folder) throws ClassNotFoundException {
		//Verify if the file is correct
		File file = new File( folder.getPath() + "\\" + namePackage + ".class");
		if( !file.exists() || file.isDirectory() ) {
			return null;
		}
		
		try {
			String uri = "file:///" + file.getAbsolutePath().replace("\\", "/");
			return Files.readAllBytes(Paths.get( new URI( uri ) ));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@SuppressWarnings("null")
	private byte[] loadClassDataWithZip(String namePackage, ZipFile zip) throws ClassNotFoundException {
		ZipEntry zipEntry = zip.getEntry(namePackage + ".class");
		
		if( zipEntry != null || !zipEntry.isDirectory() ){
			ByteArrayOutputStream baos = new ByteArrayOutputStream((int) zipEntry.getSize());
			byte [] buffer = new byte [1024];
			try {
				BufferedInputStream bis = new BufferedInputStream(zip.getInputStream(zipEntry));
				
				while( bis.available() > 0 ){
					int nb = bis.read(buffer, 0, 1024);
					baos.write(buffer, 0, nb);
				}
				
				return baos.toByteArray();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	

	public static void main(String[] args) {
		try {
			List<File> listPath = new ArrayList<>();
			listPath.add(new File("../TD2/bin/"));
			
			System.out.println("// --------------------------------------  Test class loader");
			ExplorateurClassLoader myClassLoader = new ExplorateurClassLoader(listPath);
			Class<?> cl = myClassLoader.loadClass("unice.fr.miage.analyseur.AnalyseurDeClasse");
			
			System.out.println("Class Loader : " + cl.getClassLoader().getClass().getName());
			System.out.println("Class : " + cl.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			List<File> listPath = new ArrayList<>();
			listPath.add(new File("./lib/TD2.zip"));
			
			System.out.println("// --------------------------------------  Test class zip");
			ExplorateurClassLoader myClassLoader = new ExplorateurClassLoader(listPath);
			Class<?> cl = myClassLoader.loadClass("unice.fr.miage.analyseur.AnalyseurDeClasse");
			
			System.out.println("Class Loader : " + cl.getClassLoader().getClass().getName());
			System.out.println("Class : " + cl.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			List<File> listPath = new ArrayList<>();
			listPath.add(new File("./lib/TD2.jar"));
			
			System.out.println("// --------------------------------------  Test class jar");
			ExplorateurClassLoader myClassLoader = new ExplorateurClassLoader(listPath);
			Class<?> cl = myClassLoader.loadClass("unice.fr.miage.analyseur.AnalyseurDeClasse");
			
			System.out.println("Class Loader : " + cl.getClassLoader().getClass().getName());
			System.out.println("Class : " + cl.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
