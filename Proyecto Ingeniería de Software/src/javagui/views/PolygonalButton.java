package javagui.views;

import java.awt.Point;
import java.awt.Polygon;

public class PolygonalButton extends InvisibleButton {
	
	private Polygon area = null;
	
	public PolygonalButton(Polygon area){
		this.area = area;
	}

	@Override
	public boolean contains(Point point) {
		return area.contains(point);
	}

}
