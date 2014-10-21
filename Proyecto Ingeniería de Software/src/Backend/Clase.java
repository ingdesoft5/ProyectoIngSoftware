package Backend;

import java.util.Stack;


public class Clase {
	public String nombreClase;
	public String id;
	public Stack<Metodo> metodos = new Stack<Metodo>();
	public Stack<Atributo> atributos = new Stack<Atributo>();
	
	public Clase(String nombreClase, String id){
		this.nombreClase = nombreClase;
		this.id = id;
		System.out.print("	Se ha creado la clase " + nombreClase + "(id: " + id + ") " + "\n");
	}
	public void AgregarMetodos(Metodo m){
		metodos.add(m);
		//System.out.println("		Se ha agregado el método "+ metodo + " a la clase " + nombreClase);
		if(m.parametros.get(0) != null){
			System.out.println("		El método " + m.nombre + " recibe los siguientes parámetros:");
			for(int i = 0 ; i < m.parametros.size(); i++){
				System.out.println("			->" + m.parametros.get(i).nombre + " de tipo " + m.parametros.get(i).tipo + "\n");
			}
		}
	}
	
	public void AgregarAtributos(Atributo a){
		//String vis;
		//if(a.visibilidad == "+"){
		//	 = "público";
		//}
		//else if(visibilidad == "-"){
		//	vis = "privado";
		//}
		//else{
		//	vis = "protegido";
		//}
		//String atributo = visibilidad + nombreAtributo + ":" + tipo;
		atributos.add(a);
		//System.out.print("		Se ha agregado el atributo " + nombreAtributo + ", de tipo "+ tipo + " y con visibilidad " + vis + " a la clase " + nombreClase + "\n");
		}
}
