package zebra;

import java.awt.Color;
import java.util.ArrayList;

public class Game {
	ArrayList<GameObject> gameObjects;
	
	GameObject player;
	
	public Game() {
		gameObjects = new ArrayList<GameObject>(0);
		
		player = Instantiate(new GameObject(new Vector(600, 600), 25, 25, Color.red, new Physics(1)));
		player.physics.velocity.x = 0.2;
		GameObject box = Instantiate(new GameObject(new Vector(645, 500), 25, 25, Color.pink, new Physics(1)));
		box.physics.velocity.x = 0.1;
		GameObject thing = Instantiate(new GameObject(new Vector(-100, 600), 25, 25, Color.red, new Physics(1)));
		thing.physics.velocity.x = 1;
		
		Instantiate(new GameObject(new Vector(997, 250), 24, 320, Color.pink));
		Instantiate(new GameObject(new Vector(640, 100), 640, 24, Color.pink));
		Instantiate(new GameObject(new Vector(396, 124), 150, 24, Color.pink));

	}
	
	void drawToScreen(Screen screen) {
		screen.objects = gameObjects;
		screen.repaint();
	}
	
	public void Update() {
		Physics.Tick(gameObjects);
	}
	
	public GameObject Instantiate(GameObject object) {
		gameObjects.add(object);
		return object;
	}
}
