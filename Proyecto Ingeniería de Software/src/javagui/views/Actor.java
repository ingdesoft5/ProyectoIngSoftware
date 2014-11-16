package javagui.views;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JLayeredPane;

public class Actor extends GroupLayout{

	public Actor(Container host, int y , int y2, String nombre) {
		super(host);
		//Graphics g = host.getGraphics();
		ImageIcon ic = new ImageIcon("src/javagui/resources/man.png");
		JLabel jlp = new JLabel(ic);
		//g.drawImage(ic.getImage(),y,y2,ic.getIconWidth(),ic.getIconHeight(), null);
		//jlp.paint(g);
		jlp.setBounds(y,y2,ic.getIconWidth(),ic.getIconHeight());
		host.add(jlp);

}
}
