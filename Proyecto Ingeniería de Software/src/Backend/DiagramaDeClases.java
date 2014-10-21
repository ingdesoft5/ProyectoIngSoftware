package Backend;

import java.util.Stack;


public class DiagramaDeClases {
	public String nombre;
	public Stack<Clase> Clases = new Stack<Clase>();
	public Stack<Relacion> Conexiones = new Stack<Relacion>();
	public DiagramaDeClases(String nombre){
		this.nombre = nombre;
		System.out.println("Se ha creado un nuevo diagrama de clases con el nombre " + nombre);
	}
	public void AgregarClases(Clase c){
		Clases.add(c);
	}
	public void AgregarConexiones(Relacion r){
		Conexiones.add(r);
	}
}
