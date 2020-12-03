package zebra;

import java.util.ArrayList;
import java.awt.Color;
import java.lang.Math;

public class Physics {
	static double collisionPadding = 0.3;
	static double gravity = 0.004;
	
	double mass = 1;
	double friction = 1.001;
	
	boolean doGravity = false;
	boolean doCollisions = true;
	boolean doEntityCollisions = false;
	boolean isStatic = true;
	boolean isGrounded = false;
	
	Vector velocity = new Vector();
	
	public Physics () {
		
	}
	
	public Physics (double mass) {
		this.mass = mass;
		isStatic = false;
		doGravity = true;
	}
	
	static void Tick(ArrayList<GameObject> objects) {
		ArrayList<Vector> newPositions = new ArrayList<Vector>(0);
		for (GameObject object : objects) {
			if(!object.physics.isStatic) {
				Vector newPosition = new Vector(object.position.x, object.position.y);
				object.physics.isGrounded = false;
				object.color = Color.red;
				// Forces and Gravity
				if (object.physics.doGravity) {
					object.physics.velocity.y -= gravity;
				}
				newPosition.x += object.physics.velocity.x;
				newPosition.y += object.physics.velocity.y;
				// Collisions
				if (object.physics.doCollisions) {
					for (GameObject other : objects) {
						if (object != other && other.physics.doCollisions && (object.physics.doEntityCollisions || other.physics.isStatic)) {
							// Collisions Math
							boolean horizontal = intersect(newPosition.x, other.position.x, object.width, other.width);
							boolean vertical = intersect(newPosition.y, other.position.y, object.height, other.height);
							if (horizontal && vertical) {
								object.color = Color.blue;
								// Pushes the object out the shortest distance (either horizontally or vertically)
								double x1 = other.position.x - other.width/2 - object.width/2 - collisionPadding;
								double x2 = other.position.x + other.width/2 + object.width/2 + collisionPadding;
								double y1 = other.position.y - other.height/2 - object.height/2 - collisionPadding;
								double y2 = other.position.y + other.height/2 + object.height/2 + collisionPadding;
								
								Vector push = new Vector();
								// Shortest horizontal
								if (Math.abs(newPosition.x - x1) < Math.abs(newPosition.x - x2)) {
									push.x = x1;
								} else {
									push.x = x2;
								}
								// Shortest vertical
								if (Math.abs(newPosition.y - y1) < Math.abs(newPosition.y - y2)) {
									push.y = y1;
								} else {
									push.y = y2;
								}
								// Shortest overall
								if (Math.abs(newPosition.x - push.x) < Math.abs(newPosition.y - push.y)) {
									newPosition.x = push.x;
									other.physics.velocity.x = object.physics.velocity.x * object.physics.mass / other.physics.mass;
									object.physics.velocity.y /= other.physics.friction;
									object.physics.velocity.x = 0;
								} else {
									newPosition.y = push.y;
									other.physics.velocity.y = object.physics.velocity.y * object.physics.mass / other.physics.mass;
									object.physics.velocity.x /= other.physics.friction;
									object.physics.velocity.y = 0;
								}
							}
						}
					}
				}
				newPositions.add(newPosition);
			} else {
				newPositions.add(object.position);
			}
		}
		// Sets positions after physics calculations
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).position = newPositions.get(i);
		}
	}
	
	static boolean intersect(double p1, double p2, double d1, double d2) {
		return p2 - d1/2 - d2/2 < p1 && p1 < p2 + d1/2 + d2/2;
	}
}
