package zebra;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Screen extends JPanel {
	
	public ArrayList<GameObject> objects;
	
	public Screen() {
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, 1280, 720);
		for (GameObject object : objects) {
			g.setColor(object.color);
			int x = (int)(object.position.x - object.width/2);
			int y = -(int)object.position.y - object.height/2 + 720;
			g.fillRect(x, y, object.width, object.height);
		}
	}
}
