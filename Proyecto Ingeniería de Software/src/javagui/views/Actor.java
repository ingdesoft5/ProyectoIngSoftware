package javagui.views;

import java.awt.Container;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Actor extends GroupLayout{

	public Actor(Container host, int y , int y2, String nombre) {
		super(host);
		ImageIcon ic = new ImageIcon("src/javagui/resources/man.png");
		JLabel jlp = new JLabel(nombre,ic,JLabel.CENTER);
		jlp.setVerticalTextPosition(JLabel.BOTTOM);
		jlp.setHorizontalTextPosition(JLabel.CENTER);
		jlp.setBounds(y,y2,ic.getIconWidth()+100,ic.getIconHeight()+50);
		jlp.setName("actor");
		host.add(jlp);

}
}
