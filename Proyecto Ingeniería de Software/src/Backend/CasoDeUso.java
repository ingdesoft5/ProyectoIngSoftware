package Backend;

public class CasoDeUso {
	public String id;
	public String name;
	
	public CasoDeUso(String id, String name){
		this.id = id;
		this.name = name;
		System.out.println("	Se ha creado un nuevo caso de uso con el nombre " + name + "(id " + id + ")");
	}
	
}
