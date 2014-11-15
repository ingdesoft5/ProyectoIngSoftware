package Backend;
import java.util.Stack;


public class Metodo {
	public String nombre;
	public String tipo;
	public Stack<Parametro> parametros = new Stack<Parametro>();
	
	public Metodo(String nombre, String tipo){
		this.nombre = nombre;
		this.tipo = tipo;
	}
	
	public void AgregarParametros(Parametro p){
		parametros.add(p);
	}
}
