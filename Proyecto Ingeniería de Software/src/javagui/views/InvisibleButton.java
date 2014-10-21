package javagui.views;

import java.awt.Point;

import javax.swing.AbstractButton;

public abstract class InvisibleButton extends AbstractButton {
	public abstract boolean contains (Point point);
	
	@Override
	public boolean isVisible(){
		return false;
	}
}
