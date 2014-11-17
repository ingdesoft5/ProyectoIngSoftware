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


		//http://www.stupidjavatricks.com/2005/10/turning-your-graphicsgraphics2d-drawings-into-an-imageicon/
		BufferedImage img = new BufferedImage( 280, 100, BufferedImage.TYPE_INT_RGB );
		Graphics g = img.getGraphics();
		g.setColor( Color.WHITE );
		g.fillRect( 0, 0, 280, 100 );
		g.setColor( Color.BLACK );
		g.fillOval( 2, 2, 276, 96 );
		g.setColor( Color.WHITE );
		g.fillOval( 3, 3, 274, 94 );
		g.setColor(Color.BLACK);
		g.drawString(nombre,50,50);
		
		ImageIcon icon = new ImageIcon( img );
		JLabel jl = new JLabel(icon);
		jl.setForeground(Color.black);
		//jl.setText("jasdjsadjajdjas");
		jl.setBounds(y,y2,icon.getIconWidth(),icon.getIconHeight());

		jl.setName("oval");
		host.add(jl);
	}

	

}
