package Backend;

public class Conexion {
	public String type;
	public String from;
	public String to;
	
	public Conexion(String type, String from, String to){
		this.type = type;
		this.from = from;
		this.to = to;
		System.out.println("	Se ha creado una conexión de tipo " + type + " desde la clase " + from + " hacia la clase " + to);
	}
	
}
