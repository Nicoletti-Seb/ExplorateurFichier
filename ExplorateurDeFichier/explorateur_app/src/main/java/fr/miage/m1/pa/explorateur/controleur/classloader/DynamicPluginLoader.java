package fr.miage.m1.pa.explorateur.controleur.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class DynamicPluginLoader <T> {

	private Class<T> superClass;
	
	public DynamicPluginLoader(Class<T> superClass) {
		this.superClass = superClass;
	}
	
	public List<Class<? extends T>> load(File file){
		
		List<Class<? extends T>> listClass = new ArrayList<>();
		searchClass(file, listClass);
		
		return listClass;
	}
	
	private void searchClass(File f, List<Class<? extends T>> listClass){
		
		DynamicClassLoader cl = new DynamicClassLoader(f);
		
		try {
			List<Class<?>> classes = cl.loadClassData();
			
			for(Class<?> c : classes) {
				if( superClass.isAssignableFrom(c) ){
					listClass.add( c.asSubclass(superClass) );
				}
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	private class DynamicClassLoader extends SecureClassLoader {

		File base;
		
		public DynamicClassLoader(File base) {
			this.base = base;
		}
		
		public List<Class<?>> loadClassData() throws ClassNotFoundException {  

			List<Class<?>> classes = new ArrayList<Class<?>>();
			
			if(base.getName().endsWith(".jar")) {
				classes = readArchive(base);
			} else if(base.getName().endsWith(".zip")) {
				classes = readArchive(base);
			}

			return classes;
		} 
		
		@SuppressWarnings("unchecked")
		private List<Class<?>> readArchive(File archive) {

			List<Class<?>> result = new ArrayList<Class<?>>();

			try {
				ZipFile zip = new ZipFile(archive);

				Enumeration<ZipEntry> entries = (Enumeration<ZipEntry>) zip.entries();

				while(entries.hasMoreElements()) {

					ZipEntry ze = entries.nextElement();

					if(ze.getName().endsWith(".class")) {

						InputStream is = zip.getInputStream(ze);

						ByteArrayOutputStream buffer = new ByteArrayOutputStream();

						int nRead;
						byte[] data = new byte[16384];

						while ((nRead = is.read(data, 0, data.length)) != -1) {
							buffer.write(data, 0, nRead);
						}

						buffer.flush();
						result.add(readClass(buffer.toByteArray(), ze.getName().replace("/", ".").replace(".class", "")));
					}
				}

				zip.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

			return result;
		}
		
		public Class<?> readClass(byte[] b, String name) {
			return super.defineClass(name, b, 0, b.length);  
		}
	}
}
