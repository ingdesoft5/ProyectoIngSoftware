package Backend;

public class Actor {
	public String type;
	public String id;
	public String name;
	
	public Actor(String type, String id, String name){
		this.type = type;
		this.id = id;
		this.name = name;
		System.out.println("	Se ha creado un nuevo actor con nombre: " + name + "(id: " + id +  ") " + "de tipo "+ type); 
	}
	
}
