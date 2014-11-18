package JUnitTests;

import static org.junit.Assert.*;

import java.util.Stack;

import javagui.views.Actor;
import javagui.views.Clases;
import javagui.views.Oval;
import Backend.*;

import javax.swing.JLayeredPane;

import org.junit.Test;

public class TestClases {

	@Test
	public void test() {
		JLayeredPane desktop = new JLayeredPane();
		int inicio = desktop.getComponentCount();
		Stack<Metodo> m = new Stack<Metodo>();
		Stack<Atributo> a = new Stack<Atributo>();
		Metodo metodo = new Metodo("Metodo Prueba","void");
		Atributo atributo = new Atributo("Atributo Prueba","String","-");
		m.push(metodo);
		a.push(atributo);
		Clases clase = new Clases(desktop,0,0,"Clase Prueba",m,a);
		//Conexion conexion = new Conexion(desktop, "isa", 0, 0, 10, 10);
		int fin = desktop.getComponentCount();
		assertNotEquals("Test de prueba Diagrama de Clases", inicio, fin);
	}

}
