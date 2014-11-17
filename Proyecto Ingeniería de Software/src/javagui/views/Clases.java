package javagui.views;

import java.awt.Container;
import java.awt.Graphics;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;

public class Clases  extends GroupLayout {

	public Clases(Container host,int x,int y, String nombreClase, String[] Metodos, String[] Atributos){
		super(host);
		Graphics g = host.getGraphics();
		g.drawRect(x, y, x+50, y+10);
		g.drawRect(x, y+10, x+50, y+10+10*Metodos.length);
		g.drawRect(x, y+10+10*Metodos.length, x+50, y+10+10*Metodos.length+Atributos.length);
		
			/*Graphics g = host.getGraphics();		
			MiCanvas canvas = new MiCanvas();
			canvas.paint(g, y);
			canvas.escribir(nombre, g , y2);
			host.add(canvas);*/


			// TODO Auto-generated constructor stub
		}
	}

