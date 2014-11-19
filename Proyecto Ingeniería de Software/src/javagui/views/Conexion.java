package javagui.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.spi.ImageInputStreamSpi;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class Conexion extends GroupLayout{
	
	public Conexion(Container host, String tipo, int InicioX, int InicioY, int FinX, int FinY){
		super(host);
		//Graphics g = host.getGraphics();
		//JLayeredPane jlp = new JLayeredPane();
		int LargoX = Math.abs(FinX - InicioX);
		int LargoY = Math.abs(FinY - InicioY);
		BufferedImage img = new BufferedImage(LargoX, LargoY, BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.getGraphics();
		g.setColor(Color.black);
		//g.setColor(Color.white);
		//g.fillRect(0, 0, LargoX, LargoY);
		
		if(tipo=="extend"){
			int x = (FinX - InicioX)/10;
			int y = (FinY - InicioY)/10;
			for(int i=0;i<5;i++){
				g.drawLine(InicioX+x*i, InicioY+y*i, InicioX+x*(i+1), InicioY+y*(i+1));
				//jlp.paint(g);
				//host.add(jlp);
				i++;
			}
			g.drawLine(FinX-5, InicioY, InicioX, InicioY);
			//jlp.paint(g);
			//host.add(jlp);
			g.drawLine(InicioX, InicioY, InicioX, InicioY);
			//jlp.paint(g);
			//host.add(jlp);			

		}
		else if(tipo=="include"){
			int x = (FinX - InicioX)/10;
			int y = (FinY - InicioY)/10;
			for(int i=0;i<5;i++){
				g.drawLine(InicioX+x*i, InicioY+y*i, InicioX+x*(i+1), InicioY+y*(i+1));
				//jlp.paint(g);
				//host.add(jlp);
				i++;
			}
		}
		else if(tipo=="basic"){
			g.drawLine(InicioX, InicioY, FinX, FinY);
		}
		else if(tipo=="isa"){
			g.drawLine(InicioX, InicioY, FinX-5, FinY-5);
			g.drawLine(FinX-5, FinY-5, FinX-5, FinY+5);
			g.drawLine(FinX-5, FinY-5, FinX, FinY);
			g.drawLine(FinX-5, FinY+5, FinX, FinY);
		}
		else if(tipo=="dependency"){
			int x = (FinX - InicioX)/10;
			int y = (FinY - InicioY)/10;
			for(int i=0;i<5;i++){
				g.drawLine(InicioX+x*i, InicioY+y*i, InicioX+x*(i+1), InicioY+y*(i+1));
				//jlp.paint(g);
				//host.add(jlp);
				i++;
			}
		}
		else if(tipo=="association"){
			g.drawLine(InicioX, InicioY, FinX, FinY);
		}
		else if(tipo=="aggregation"){
			g.drawLine(InicioX, InicioY, FinX-5, FinY-5);
			g.drawRect(FinX-5, FinY-5, FinX, FinY);
		}
		else if(tipo=="composition"){
			g.drawLine(InicioX, InicioY, FinX-5, FinY-5);
			g.drawRect(FinX-5, FinY-5, FinX, FinY);
		}
		else if(tipo=="inheritance"){
			g.drawLine(InicioX, InicioY, FinX-5, FinY-5);
			g.drawLine(FinX-5, FinY-5, FinX-5, FinY+5);
			g.drawLine(FinX-5, FinY-5, FinX, FinY);
			g.drawLine(FinX-5, FinY+5, FinX, FinY);
		}
		ImageIcon icon = new ImageIcon(img);
		JLabel jlp = new JLabel(icon);
		jlp.setName("conexion");
		jlp.setOpaque(false);
		jlp.setBounds(Math.min(InicioX,FinX), Math.min(InicioY,FinY), LargoX, LargoY);
		host.add(jlp);
}
}