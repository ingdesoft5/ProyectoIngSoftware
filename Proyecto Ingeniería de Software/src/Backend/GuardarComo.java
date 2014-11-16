package Backend;

import java.awt.Container;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GuardarComo {
	public GuardarComo(){

        }
	public void GuardarComoXML(String texto){
	    
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("XML Files",".xml"));//filtro para ver solo archivos .xml
        int seleccion = fileChooser.showSaveDialog(null);
        try{
            if (seleccion == JFileChooser.APPROVE_OPTION){//comprueba si ha presionado el boton de aceptar
                File JFC = fileChooser.getSelectedFile();
                String PATH = JFC.getAbsolutePath();//obtenemos el path del archivo a guardar
                PrintWriter printwriter = new PrintWriter(JFC);
                printwriter.print(texto);//escribe en el archivo todo lo que se encuentre en el JTextArea
                printwriter.close();//cierra el archivo

                //comprobamos si a la hora de guardar obtuvo la extension y si no se la asignamos
                if(!(PATH.endsWith(".xml"))){
                    File temp = new File(PATH+".xml");
                    JFC.renameTo(temp);//renombramos el archivo
                }
                
            }

        }catch (Exception e){//por alguna excepcion salta un mensaje de error
        }
	}
	public void GuardarComoImagen(Container c){

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PNG Files",".png"));//filtro para ver solo archivos .xml
        int seleccion = fileChooser.showSaveDialog(null);
		try {
            if (seleccion == JFileChooser.APPROVE_OPTION){//comprueba si ha presionado el boton de aceptar
                File JFC = fileChooser.getSelectedFile();
                String PATH = JFC.getAbsolutePath();//obtenemos el path del archivo a guardar
        		BufferedImage im = new BufferedImage(c.getWidth(), c.getHeight(), BufferedImage.TYPE_INT_ARGB);
        		c.paint(im.getGraphics());
                ImageIO.write(im, "PNG", new File(PATH+".png"));
            } 
		}
            catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void GuardarComoCarpeta(String texto, Container c){
      
        JFileChooser fileChooser = new JFileChooser();
        int seleccion = fileChooser.showSaveDialog(null);
        try{
            if (seleccion == JFileChooser.APPROVE_OPTION){//comprueba si ha presionado el boton de aceptar
                File JFC = fileChooser.getSelectedFile();
                String PATH = JFC.getAbsolutePath();//obtenemos el path del archivo a guardar
                new File(PATH).mkdirs();
                PATH = PATH+"/"+JFC.getName();
                File archivo = new File(PATH); 
                //XML
                PrintWriter printwriter = new PrintWriter(archivo);
                printwriter.print(texto);//escribe en el archivo todo lo que se encuentre en el JTextArea
                printwriter.close();//cierra el archivo
                File temp = new File(PATH+"_XML.xml");
                archivo.renameTo(temp);//renombramos el archivo
                //PNG
                BufferedImage im = new BufferedImage(c.getWidth(), c.getHeight(), BufferedImage.TYPE_INT_ARGB);
        		c.paint(im.getGraphics());
        		ImageIO.write(im, "PNG", new File(PATH+"_PNG.png"));
                
                
            }

        }catch (Exception e){//por alguna excepcion salta un mensaje de error
        }
        }    
}
