package fr.miage.m1.pa.explorateur.enums;

public enum TypeModele {

	MODEL("MODEL"),
	DECORATOR("DECORATOR");
	
	private String name;
	
	private TypeModele(String name) {
		this.name = name;
	}
	
	public String toString(){
		return name;
	}
}
