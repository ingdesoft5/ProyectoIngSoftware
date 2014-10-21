package javagui.views;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.List;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class InvisibleButtonImagePanel extends JPanel{

	private BufferedImage image = null;
	private ArrayList<InvisibleButton> buttons = new ArrayList<>();
	
	public InvisibleButtonImagePanel(BufferedImage image){
		this.image = image;
	}
	public void add(InvisibleButton button){
		buttons.add(button);
	}
	public void remove(InvisibleButton button){
		buttons.remove(button);
	}
	
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(image.getWidth(), image.getHeight());
	}
	
	@Override
	public void processKeyEvent(KeyEvent event){
		for(InvisibleButton button : buttons ){
			if(button.isFocusOwner()){
				button.dispatchEvent(event);
			}
		}
	super.processKeyEvent(event);
	}
	
	@Override
	public void processMouseEvent(MouseEvent event){
		for(InvisibleButton button : buttons ){
			if(button.contains(event.getPoint())){
				button.dispatchEvent(event);
			}
		}
		super.processMouseEvent(event);
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.drawImage(image, 0,0,null);
		super.paintComponent(g);
	}
}
