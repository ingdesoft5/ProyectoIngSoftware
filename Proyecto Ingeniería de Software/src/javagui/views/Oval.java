package javagui.views;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.TextArea;
import java.awt.image.BufferedImage;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Group;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class Oval extends GroupLayout{

	public Oval(Container host, int y , int y2, String nombre) {
		super(host);
		/*Graphics g = host.getGraphics();		
		MiCanvas canvas = new MiCanvas();
		canvas.paint(g, y);
		canvas.escribir(nombre, g , y2);
		host.add(canvas);*/
		ImageIcon ic = new ImageIcon("src/javagui/resources/oval.png");
		JEditorPane jl = new JEditorPane();
		jl.setBounds(y,y2,ic.getIconWidth(),ic.getIconHeight());
		host.add(jl);

		// TODO Auto-generated constructor stub
	}

	

}
