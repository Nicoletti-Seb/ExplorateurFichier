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

	public String toString(){
		return name;
	}
}
