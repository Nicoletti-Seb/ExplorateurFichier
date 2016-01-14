package fr.miage.m1.pa.explorateur.enums;

public enum Title {
	
	NAME("Nom"),
	MODIFIED("Modifié le"),
	TYPE("Type"),
	SIZE("Taille");

	private String name = "";

	//Constructeur
	Title(String name){
		this.name = name;
	}
	
	public static Title fromString(String name) {
		
		switch(name) {
		
		case "Nom" : return NAME;
		case "Modifié le" : return MODIFIED;
		case "Type" : return TYPE;
		case "Taille" : return SIZE;
		
		}
		
		return NAME;
	}

	public String toString(){
		return name;
	}
}
