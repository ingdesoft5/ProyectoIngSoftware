package JUnitTests;

import static org.junit.Assert.*;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.junit.Test;

public class TestCargarArchivo {

	@Test
	public void testCargar() {
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new FileNameExtensionFilter("XML files", "xml"));
		int respuesta = fc.showOpenDialog(null);
		File archivoElegido = null;
		if(respuesta == JFileChooser.APPROVE_OPTION)
		{
			archivoElegido = fc.getSelectedFile();
		}
		
		assertNotEquals("Test de cargar archivo", archivoElegido.getPath(), "");
	}

}
