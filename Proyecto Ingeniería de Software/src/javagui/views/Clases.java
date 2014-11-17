package javagui.views;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Stack;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JLabel;

import Backend.Atributo;
import Backend.Metodo;

public class Clases  extends GroupLayout {

	public Clases(Container host,int x,int y, String nombreClase, Stack<Metodo> metodos2, Stack<Atributo> atributos2){
		super(host);
		String[] metodos = new String[metodos2.size()];
		for(int i = 0 ; i<metodos2.size() ; i++){
			metodos[metodos.length-i-1] = metodos2.pop().nombre;
		}
		String[] atributos = new String[atributos2.size()];
		for(int i = 0 ; i<atributos2.size() ; i++){
			atributos[atributos.length-i-1] = atributos2.pop().nombre;
		}
		BufferedImage img = new BufferedImage(10+10*metodos.length+10*atributos.length, 200, BufferedImage.TYPE_INT_RGB );
		Graphics g = img.getGraphics();
		g.setColor(Color.black);
		g.drawRect(0, 0, x+50, y+10);
		g.drawRect(0, y+10, x+50, y+10+10*metodos.length);
		g.drawRect(0, y+10+10*metodos.length, x+50, y+10+10*metodos.length+atributos.length);
		g.setColor(Color.white);
		g.drawRect(2, y-2, x+50, y+10);
		g.drawRect(2, y+8, x+50, y+10+10*metodos.length);
		g.drawRect(2, y+8+10*metodos.length, x+50, y+10+10*metodos.length+atributos.length);
		String text = nombreClase + "\n";
		for(int i = 0; i<metodos.length;i++){
			text = text + metodos[i] + "\n";
		}
		for(int i = 0; i<atributos.length;i++){
			text = text + atributos[i] + "\n";
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

