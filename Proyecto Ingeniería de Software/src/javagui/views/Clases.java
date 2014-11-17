package javagui.views;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JLabel;

public class Clases  extends GroupLayout {

	public Clases(Container host,int x,int y, String nombreClase, String[] Metodos, String[] Atributos){
		super(host);
		BufferedImage img = new BufferedImage(10+10*Metodos.length+10*Atributos.length, 200, BufferedImage.TYPE_INT_RGB );
		Graphics g = img.getGraphics();
		g.setColor(Color.black);
		g.drawRect(0, 0, x+50, y+10);
		g.drawRect(0, y+10, x+50, y+10+10*Metodos.length);
		g.drawRect(0, y+10+10*Metodos.length, x+50, y+10+10*Metodos.length+Atributos.length);
		g.setColor(Color.white);
		g.drawRect(2, y-2, x+50, y+10);
		g.drawRect(2, y+8, x+50, y+10+10*Metodos.length);
		g.drawRect(2, y+8+10*Metodos.length, x+50, y+10+10*Metodos.length+Atributos.length);
		String text = nombreClase + "\n";
		for(int i = 0; i<Metodos.length;i++){
			text = text + Metodos[i] + "\n";
		}
		for(int i = 0; i<Atributos.length;i++){
			text = text + Atributos[i] + "\n";
		}
		g.drawString(text, 0, 0);
		ImageIcon icon = new ImageIcon(img);
		JLabel jl = new JLabel(icon);
		jl.setForeground(Color.black);
		
	
			/*Graphics g = host.getGraphics();		
			MiCanvas canvas = new MiCanvas();
			canvas.paint(g, y);
			canvas.escribir(nombre, g , y2);
			host.add(canvas);*/


			// TODO Auto-generated constructor stub
		}
	}

