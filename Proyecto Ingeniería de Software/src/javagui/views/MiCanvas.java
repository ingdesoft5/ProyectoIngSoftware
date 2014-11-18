package javagui.views;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;

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

}
public void escribir(String text, Graphics g, int y){
	Graphics2D g2 = (Graphics2D) g;
	g2.drawString(text, 200, y);
}

public void imagen(Container host, Graphics g, int x, int y){
JLayeredPane jlp = new JLayeredPane();
ImageIcon ic = new ImageIcon("\\src\\javagui\\resources\\man.png");
g.drawImage(ic.getImage(),x,y,40,50, null);
jlp.paint(g);
host.add(jlp);


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
