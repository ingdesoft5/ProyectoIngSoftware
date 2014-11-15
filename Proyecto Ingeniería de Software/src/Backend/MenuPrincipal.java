package Backend;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MenuPrincipal {
	public Lector l;
	public void Abrir(){
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new FileNameExtensionFilter("XML files", "xml"));
		int respuesta = fc.showOpenDialog(null);
		File archivoElegido = null;
		if(respuesta == JFileChooser.APPROVE_OPTION)
		{
			archivoElegido = fc.getSelectedFile();
		}
		l = new Lector();
		if(archivoElegido != null){
			l.leer(archivoElegido);
		}
	}

}
