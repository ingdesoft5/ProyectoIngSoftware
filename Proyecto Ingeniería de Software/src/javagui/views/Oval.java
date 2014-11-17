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
import java.io.File;
import java.net.MalformedURLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Group;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
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
		BufferedImage bi = new BufferedImage(280, 100, BufferedImage.TYPE_INT_RGB);
		Graphics g = bi.getGraphics();
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(2));
		g2.setColor(Color.white);
		g2.fillRect(150, y, 280, 100);
		g2.setColor(Color.black);
		g2.drawOval(150, y, 280, 100);

		ImageIcon ic = new ImageIcon();
		JLabel jl = new JLabel(ic);
		ic.paintIcon(jl, g, y, y2);
		jl.setBounds(y,y2,ic.getIconWidth(),ic.getIconHeight());
		jl.setName("oval");
		host.add(jl);

		// TODO Auto-generated constructor stub
	}

	

}
