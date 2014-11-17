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
		BufferedImage img = new BufferedImage(200,2*(y+10+10*metodos.length)+atributos.length + 3, BufferedImage.TYPE_INT_RGB );
		Graphics g = img.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0,0, 200, 2*(y+10+10*metodos.length)+atributos.length + 3);
		g.setColor(Color.BLACK);
		g.drawRect(0, y, x+50, 25);
		g.drawRect(0, y+25, x+50, 25+25*metodos.length);
	//	g.drawRect(0, y+10+10*metodos.length, x+50, y+10+10*metodos.length+atributos.length);
		String text = nombreClase + "\n";
		for(int i = 0; i<metodos.length;i++){
			text = text + metodos[i] + "\n";
		}
		for(int i = 0; i<atributos.length;i++){
			text = text + atributos[i] + "\n";
		}
		g.drawString(text, 10, 10);
		ImageIcon icon = new ImageIcon(img);
		JLabel jl = new JLabel(icon);
		jl.setBounds(x,y,icon.getIconWidth(),icon.getIconHeight());
		host.add(jl);

			// TODO Auto-generated constructor stub
		}
	}

