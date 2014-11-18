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
		
		//773 × 587
		if(280+y>773){
			if(y2+100>587){
				host.setSize(280+y, y2+100);
				host.setPreferredSize(new Dimension(280 + y,100 + y2));
			}
			else{
				host.setSize(280 + y, 587);
				host.setPreferredSize(new Dimension(280 + y,587));
			}
		}
		else{
			if(280+y>587){
				host.setSize(773, 280 + y);
				host.setPreferredSize(new Dimension(773,280 + y));
			}
			else{
				host.setSize(773, 587);
				host.setPreferredSize(new Dimension(773,587));
			}
		}
		
		ImageIcon icon = new ImageIcon( img );
		JLabel jl = new JLabel(icon);
		jl.setForeground(Color.black);
		jl.setBounds(y,y2,icon.getIconWidth(),icon.getIconHeight());
		jl.setName("oval");
		host.add(jl);
	}

	

}
