package javagui.views;


import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Conexion extends GroupLayout{
	
	public Conexion(Container host, String tipo, int InicioX, int InicioY, int FinX, int FinY){
		super(host);

		int LargoX = Math.abs(FinX - InicioX);
		int LargoY = Math.abs(FinY - InicioY);
		if(LargoX == 0){
			LargoX = 3;
		}
		if(LargoY == 0){
			LargoY = 3;
		}
		BufferedImage img = new BufferedImage(LargoX, LargoY, BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.getGraphics();
		g.setColor(Color.BLACK);

		if(tipo=="extend"){
			int x = (FinX - InicioX)/10;
			int y = (FinY - InicioY)/10;
			for(int i=0;i<5;i++){
				g.setColor(Color.BLACK);
				g.drawLine(InicioX+x*i, InicioY+y*i, InicioX+x*(i+1), InicioY+y*(i+1));
				i++;
			}
			g.setColor(Color.BLACK);
			//g.drawLine(FinX-5, InicioY, InicioX, InicioY);
			//g.drawLine(InicioX, InicioY, InicioX, InicioY);	
		}
		else if(tipo=="include"){
			int x = (FinX - InicioX)/10;
			int y = (FinY - InicioY)/10;
			for(int i=0;i<5;i++){
				g.setColor(Color.BLACK);
				g.drawLine(InicioX+x*i, InicioY+y*i, InicioX+x*(i+1), InicioY+y*(i+1));
				i++;
			}
		}
		else if(tipo=="basic"){
			g.setColor(Color.BLACK);
			g.drawLine(InicioX, InicioY, FinX, FinY);
		}
		else if(tipo=="isa"){
			g.setColor(Color.BLACK);
			g.drawLine(InicioX, InicioY, FinX-5, FinY-5);
			g.drawLine(FinX-5, FinY-5, FinX-5, FinY+5);
			g.drawLine(FinX-5, FinY-5, FinX, FinY);
			g.drawLine(FinX-5, FinY+5, FinX, FinY);
		}
		else if(tipo=="dependency"){
			int x = (FinX - InicioX)/10;
			int y = (FinY - InicioY)/10;
			for(int i=0;i<5;i++){
				g.setColor(Color.BLACK);
				g.drawLine(InicioX+x*i, InicioY+y*i, InicioX+x*(i+1), InicioY+y*(i+1));
				i++;
			}
		}
		else if(tipo=="association"){
			g.setColor(Color.BLACK);
			g.drawLine(InicioX, InicioY, FinX, FinY);
		}
		else if(tipo=="aggregation"){
			g.setColor(Color.BLACK);
			g.drawLine(InicioX, InicioY, FinX-5, FinY-5);
			g.drawRect(FinX-5, FinY-5, FinX, FinY);
		}
		else if(tipo=="composition"){
			g.setColor(Color.BLACK);
			g.drawLine(InicioX, InicioY, FinX-5, FinY-5);
			g.drawRect(FinX-5, FinY-5, FinX, FinY);
		}
		else if(tipo=="inheritance"){
			g.setColor(Color.BLACK);
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