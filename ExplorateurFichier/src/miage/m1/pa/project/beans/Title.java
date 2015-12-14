package miage.m1.pa.project.beans;

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
