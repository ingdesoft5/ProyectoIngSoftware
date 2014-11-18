package JUnitTests;

import static org.junit.Assert.*;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import javagui.views.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import org.junit.Test;

import Backend.GuardarComo;
import Backend.Lector;

public class TestElegirGuardar {

	@Test
   public void TestElegir(){
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new FileNameExtensionFilter("XML files", "xml"));
		int respuesta = fc.showOpenDialog(null);
		File archivoElegido = null;
		if(respuesta == JFileChooser.APPROVE_OPTION)
		{
			archivoElegido = fc.getSelectedFile();
		}
		assertNotEquals("Se eligio archivo", archivoElegido, null);
	}
	
	}


