package zebra;

import java.awt.Color;
import java.util.ArrayList;

public class Game {
	ArrayList<GameObject> gameObjects;
	
	GameObject player;
	
	public Game() {
		gameObjects = new ArrayList<GameObject>(0);
		
		player = new GameObject(new Vector(640, 720), 25, 25, Color.red, new Physics(1));
		
		GameObject floor = new GameObject(new Vector(640, 200), 1000, 50, Color.pink);
		floor.physics.velocity.y = -0.1;
		
		gameObjects.add(player);
		gameObjects.add(floor);
	}
	
	void drawToScreen(Screen screen) {
		screen.objects = gameObjects;
		screen.repaint();
	}
	
	public void Update() {
		Physics.Tick(gameObjects);
	}
}
