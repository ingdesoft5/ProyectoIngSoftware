package javagui.views;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Oval extends GroupLayout{
	public Oval(Container host, int y , int y2, String nombre) {
		super(host);

		//http://www.stupidjavatricks.com/2005/10/turning-your-graphicsgraphics2d-drawings-into-an-imageicon/
		BufferedImage img = new BufferedImage( 280, 100, BufferedImage.TYPE_INT_ARGB );
		Graphics g = img.getGraphics();

		g.setColor( Color.BLACK );
		g.fillOval( 2, 2, 276, 96 );
		g.setColor( Color.WHITE );
		g.fillOval( 3, 3, 274, 94 );
		g.setColor(Color.BLACK);
		g.drawString(nombre,50,50);
		
		ImageIcon icon = new ImageIcon( img );
		JLabel jl = new JLabel(icon);
		jl.setForeground(Color.black);
		jl.setBounds(y,y2,icon.getIconWidth(),icon.getIconHeight());
		jl.setName("oval");
		host.add(jl);
	}


}
