package javagui.views;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.GroupLayout;

public class Actor extends GroupLayout{

	public Actor(Container host, int y , int y2) {
		super(host);
		Graphics g = host.getGraphics();
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image im = tk.getImage("/javagui/resources/man.png");
		
		MiCanvas canvas = new MiCanvas();
		canvas.imagen(im, g);
		host.add(canvas);
		//TextArea t = new TextArea();
		//t.append("hola");
		//host.add(t);
		//

}
}
