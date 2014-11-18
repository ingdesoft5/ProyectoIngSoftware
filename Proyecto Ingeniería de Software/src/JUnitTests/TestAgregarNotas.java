package JUnitTests;

import static org.junit.Assert.*;

import java.io.File;
import java.net.MalformedURLException;

import javax.swing.JLayeredPane;
import javax.swing.JTextPane;

import org.junit.Test;

public class TestAgregarNotas {

	@Test
	public void TestAgregarNota() {
		JLayeredPane desktop = new JLayeredPane();
		int inicio = desktop.getComponentCount();
		String imgsrc = "";
		try {
			imgsrc = new File("src/javagui/resources/nota2.png").toURI().toURL().toExternalForm();
		} catch (MalformedURLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		String html = "<img src=\""+imgsrc+"\" width=\"74\" height=\"85\">";
		String nombre = "editorPaneN";
		JTextPane editorPane = new JTextPane();
		editorPane.setName(nombre);
		editorPane.setContentType("text/html");
		editorPane.setText(html +"<br> contenido");
		desktop.add(editorPane);
		int fin = desktop.getComponentCount();
		assertNotEquals("Test Agregar Nota", inicio, fin);
	}

}
