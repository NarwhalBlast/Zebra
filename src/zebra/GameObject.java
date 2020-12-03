package zebra;

import java.awt.Color;

public class GameObject {
	Vector position;
	int width;
	int height;
	Color color;
	
	Physics physics = new Physics();
	
	public GameObject(Vector position, int width, int height, Color color) {
		this.position = position;
		this.width = width;
		this.height = height;
		this.color = color;
	}
	
	public GameObject(Vector position, int width, int height, Color color, Physics physics) {
		this.position = position;
		this.width = width;
		this.height = height;
		this.color = color;
		this.physics = physics;
	}
}
