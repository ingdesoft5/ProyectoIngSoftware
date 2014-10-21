package Backend;
import java.util.Stack;


public class DiagramaCasosDeUso {
	private String text;
	public String nombre;
	public Stack<Actor> Actores = new Stack<Actor>();
	public Stack<CasoDeUso> CasosDeUso = new Stack<CasoDeUso>();
	public Stack<Conexion> Conexiones = new Stack<Conexion>();

	public DiagramaCasosDeUso(String nombre){
		this.nombre = nombre;
		System.out.println("Se ha creado un nuevo diagrama de casos de uso con el nombre " + nombre);
	}
	
	public void AgregarActores(Actor a){
		Actores.add(a);
	}
	
	public void AgregarCasos(CasoDeUso c){
		CasosDeUso.add(c);
	}
	
	public void AgregarConexiones(Conexion d){
		Conexiones.add(d);
	}

	public String Agregar() {
		
		return null;
	}
}
