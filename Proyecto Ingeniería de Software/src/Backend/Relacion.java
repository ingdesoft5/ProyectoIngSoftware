package Backend;


public class Relacion {
	public void Relacionar(String tipo, Clase from, Clase to ){
		if(tipo == "asociacion"){
			System.out.println("Se ha creado una relación de asociación desde " + from + " hacia" + to + "\n");
		}
		else if(tipo == "composicion"){
			System.out.println("Se ha creado una relación de composición desde " + from + " hacia" + to + "\n");
		}
		else if(tipo == "dependencia"){
			System.out.println("Se ha creado una relación de dependencia desde " + from + " hacia" + to + "\n");
		}
		else if(tipo == "agregacion"){
			System.out.println("Se ha creado una relación de agregación desde " + from + " hacia" + to + "\n");
		}
		else if(tipo == "generalizacion"){
			System.out.println("Se ha creado una relación de generalización desde " + from + " hacia" + to + "\n");
		}
	}

}
