package javagui.views;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class MiCanvas extends Canvas implements KeyListener, MouseListener, MouseMotionListener{

	int x=-1, y=-1, x2=-1, y2=-1;
	boolean nnw = false;
	
	public MiCanvas(){
	addKeyListener(this);
	addMouseListener(this);
	addMouseMotionListener(this);
	this.setBackground(Color.white);
}

public void paint(Graphics g, int y){
	Graphics2D g2 = (Graphics2D) g;
	g2.setStroke(new BasicStroke(2));
	g2.setColor(Color.black);
	g2.drawOval(150, y, 280, 100);

	/*if(x2 != -1 && x != -1){
		//g2.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer)
		//g2.drawLine(x,y,x2,y2);
		g2.drawOval(100, 100, 400, 100);
	}
	if(nnw){
		g2.setColor(Color.white);
		g2.fillRect(-10, -10, 600, 600);
	}*/
}
public void escribir(String text, Graphics g, int y){
	Graphics2D g2 = (Graphics2D) g;
	g2.drawString(text, 190, y);
}

public void imagen(String path, Graphics g){
	URL url = this.getClass().getResource(path);
	try {
		BufferedImage img = ImageIO.read(url);
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(img, 0,0,null);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}

@Override
public void mouseDragged(MouseEvent e) {
	// TODO Auto-generated method stub
	x2=e.getX();
	y2=e.getY();
	paint(this.getGraphics());
	x=x2;
	y=y2;
	
}

@Override
public void mouseMoved(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseClicked(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mousePressed(MouseEvent e) {
	// TODO Auto-generated method stub
	x=e.getX();
	y=e.getY();
	paint(this.getGraphics());
	
}

@Override
public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub
	x=-1;
	x2=-1;
}

@Override
public void mouseEntered(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void keyPressed(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	
}
}
