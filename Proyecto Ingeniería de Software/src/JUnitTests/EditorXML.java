package JUnitTests;

import static org.junit.Assert.*;

import javax.swing.JTextPane;

import org.junit.Test;

public class EditorXML {

	@Test
	public void TestEscribirEditorXML() {
		JTextPane textArea = new JTextPane();
		String inicio = textArea.getText();
		textArea.setText("Prueba Escribir Texto");
		String fin = textArea.getText();
		assertNotEquals("Prueba Escribir en Editor XML", inicio, fin);
	}
	
	@Test
	public void TestBorrarEditorXML(){
		JTextPane textArea = new JTextPane();
		textArea.setText("Prueba Borrar Texto");
		String inicio = textArea.getText();
		textArea.setText("");
		String fin = textArea.getText();
		assertNotEquals("Prueba Borrar en Editor XML", inicio, fin);
	}

}
