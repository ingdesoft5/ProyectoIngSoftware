package javagui.views;

import java.awt.Container;
import java.awt.Graphics;

import javax.swing.GroupLayout;

public class Actor extends GroupLayout{

	public Actor(Container host, int y , int y2) {
		super(host);
		Graphics g = host.getGraphics();
		MiCanvas canvas = new MiCanvas();
		canvas.imagen("/javagui/resources/Persona.gif", g);
		host.add(canvas);
		//TextArea t = new TextArea();
		//t.append("hola");
		//host.add(t);
		//

}
}
