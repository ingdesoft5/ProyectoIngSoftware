package javagui.views;

import java.awt.Canvas;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.TextArea;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Group;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class Oval extends GroupLayout{

	public Oval(Container host, int y , int y2, String nombre) {
		super(host);
		Graphics g = host.getGraphics();
		MiCanvas canvas = new MiCanvas();
		canvas.paint(g, y);
		canvas.escribir(nombre, g , y2);
		host.add(canvas);
		// TODO Auto-generated constructor stub
	}

	

}
