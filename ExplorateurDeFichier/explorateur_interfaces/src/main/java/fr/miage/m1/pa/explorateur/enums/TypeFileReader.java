package fr.miage.m1.pa.explorateur.enums;

public enum TypeFileReader {

	FILE_READER("FILE_READER"),
	DECORATOR("DECORATOR");
	
	private String name;
	
	private TypeFileReader(String name) {
		this.name = name;
	}
	
	public String toString(){
		return name;
	}
}
