package JUnitTests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextPane;

import org.junit.Test;

public class TestColorTipografia {

	@Test
	public void TestCambiarColor() {
		JTextPane textArea = new JTextPane();
		Color inicio = textArea.getForeground();
		textArea.setForeground(Color.RED);
		Color fin = textArea.getForeground();
		assertNotEquals("Test Cambiar Color Tipografia",inicio,fin);
	}
	
	/*@Test
	public void TestCambiarTipografia(){
		JTextPane textArea = new JTextPane();
		Font inicio = textArea.getFont();
		textArea.setFont(Font.ITALIC);
	}*/

}
