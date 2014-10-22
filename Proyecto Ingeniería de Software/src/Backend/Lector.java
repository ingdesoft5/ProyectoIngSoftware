package Backend;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class Lector {
	public DiagramaCasosDeUso diagCU;
	public DiagramaDeClases diagC;
	public String tipo;
	public void leer(File xmlFile){
		SAXBuilder builder = new SAXBuilder();
		 
		  try {
	 
			Document document = (Document) builder.build(xmlFile);
			Element rootNode = document.getRootElement();
			if(rootNode.getName() == "UseCaseDiagram"){
				tipo = "UCD"; 
				String nombre = rootNode.getAttributeValue("name");
				diagCU = new DiagramaCasosDeUso(nombre);
				Element node1 = (Element) rootNode.getChildren("actors").get(0);
				Element node2 = (Element) rootNode.getChildren("usecases").get(0);
				Element node3 = (Element) rootNode.getChildren("connections").get(0);

				List<Element> list1 = node1.getChildren("actor");
				List<Element> list2 = node2.getChildren("usecase");
				List<Element> list3 = node3.getChildren("connection");
				
				for (int i = 0; i < list1.size(); i++) {
					Element node = (Element) list1.get(i);
					Actor a = new Actor(node.getAttributeValue("type"), node.getAttributeValue("id"), node.getAttributeValue("name"));
					diagCU.AgregarActores(a);
								 
				}
				for (int i = 0; i < list2.size(); i++) {
					Element node = (Element) list2.get(i);
					CasoDeUso cu = new CasoDeUso(node.getAttributeValue("id"), node.getAttributeValue("name"));
					diagCU.AgregarCasos(cu);
			 
				}
				for (int i = 0; i < list3.size(); i++) {
					Element node = (Element) list3.get(i);
					Conexion c = new Conexion(node.getAttributeValue("type"), node.getAttributeValue("from"), node.getAttributeValue("to"));
					diagCU.AgregarConexiones(c);
			 
				}
			}

			else if(rootNode.getName() == "ClassDiagram"){
				tipo = "CD";
				diagC = new DiagramaDeClases(rootNode.getAttributeValue("name"));
				List<Element> list = rootNode.getChildren("class");
				for (int i = 0; i < list.size(); i++) {
					Element node = (Element) list.get(i);
					Clase c = new Clase(node.getAttributeValue("name"), node.getAttributeValue("id"));
					List<Element> listA = node.getChildren("attributes");
					for(int l = 0; l<listA.size(); l++){
						Element nodeAs = (Element) listA.get(l);
						List<Element> lista = nodeAs.getChildren("att");
						for(int j = 0; j < lista.size(); j++){
							Element nodeA = (Element) lista.get(j);
							Atributo a = new Atributo(nodeA.getAttributeValue("name"), nodeA.getAttributeValue("type"), nodeA.getAttributeValue("visibility"));
							c.AgregarAtributos(a);
						}
					}
					List<Element> listMs = node.getChildren("methods");
					for(int l = 0; l<listMs.size() ; l++){
						Element nodeMs = (Element) listMs.get(l);
						List<Element> listM = nodeMs.getChildren("method");
						for(int j = 0; j < listM.size(); j++){
							Element nodeM = (Element) listM.get(j);
							Metodo m = new Metodo(nodeM.getAttributeValue("name"), nodeM.getAttributeValue("type"));
							List<Element> listP = nodeM.getChildren("param");
							for(int k = 0; k < listP.size(); k++){
								Element nodeP = (Element) listP.get(k);
								Parametro p = new Parametro(nodeP.getAttributeValue("name"), nodeP.getAttributeValue("type"));
								m.AgregarParametros(p);
							}
							c.AgregarMetodos(m);
						}
					}
					
					diagC.AgregarClases(c);
				}

			}
	 

	 
		  } catch (IOException io) {
			  tipo = "error";
			System.out.println(io.getMessage());
		  } catch (JDOMException jdomex) {
			 tipo = "error";
			//System.out.println(jdomex.getMessage());
		  }
	}
	}
