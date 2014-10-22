package javagui.views;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.GroupLayout;

public class Actor extends GroupLayout{

	public Actor(Container host, int y , int y2, String nombre) {
		super(host);
		Graphics g = host.getGraphics();
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image im = tk.getImage("src\\javagui\\resources\\man.png");
		
		MiCanvas canvas = new MiCanvas();
		canvas.imagen(im, g, y, y2);
		
		//canvas.escribir(nombre,g,y2)
		host.add(canvas);

}
}
