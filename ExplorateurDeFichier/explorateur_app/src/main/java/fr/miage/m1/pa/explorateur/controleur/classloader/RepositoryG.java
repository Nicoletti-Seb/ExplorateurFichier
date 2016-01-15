package fr.miage.m1.pa.explorateur.controleur.classloader;

import java.io.File;
import java.nio.file.SimpleFileVisitor;
import java.util.ArrayList;
import java.util.List;


public class RepositoryG <T> {
	
	private Class<T> superClass;
	private ExplorateurClassLoader classLoader;
	private File file;
	
	public RepositoryG(File file, Class<T> superClass) {
		this.file = file;
		this.superClass = superClass;
		
		//init class loader
		List<File> paths = new ArrayList<>();
		paths.add(file);
		
		classLoader = new ExplorateurClassLoader(paths);
	}

	
	public List<Class<? extends T>> load(){
		
		if( !file.isDirectory() ){
			return null;
		}
		
		List<Class<? extends T>> listClass = new ArrayList<>();
		searchClass("", file, listClass);
		
		return listClass;
	}
	
	
	private void searchClass(String nameClass, File folder, List<Class<? extends T>> listClass){
		
		for( File f : folder.listFiles() ){
			
			if( f.isDirectory() ){
				if( nameClass.isEmpty() ){
					searchClass( f.getName(), f, listClass);
				}
				else{
					searchClass( nameClass + "." + f.getName(), f, listClass);
				}
			}
			else if( f.getName().endsWith(".class")  ){
				try {
					String nameFile = f.getName();
					nameFile = nameFile.substring(0, nameFile.lastIndexOf(".class") );
					
					Class<?> cl = classLoader.loadClass(nameClass+"."+nameFile);
					if( superClass.isAssignableFrom(cl) ){
						listClass.add( cl.asSubclass(superClass) );
					}
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		File folder = new File("../TD1/bin");
		RepositoryG<SimpleFileVisitor> repository = new RepositoryG<>(folder, SimpleFileVisitor.class);
		System.out.println(repository.load());
	}
}
