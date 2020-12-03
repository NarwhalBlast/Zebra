package zebra;

import java.util.ArrayList;

public class Physics {
	double mass = 1;
	
	boolean doGravity = false;
	boolean doCollisions = true;
	boolean isStatic = true;
	boolean isGrounded = false;
	
	Vector netForce = new Vector();
	Vector velocity = new Vector();
	
	public Physics () {
		
	}
	
	public Physics (double mass) {
		this.mass = mass;
		isStatic = false;
		doGravity = true;
	}
	
	static void Tick(ArrayList<GameObject> objects) {
		for (GameObject object : objects) {
			if(!object.physics.isStatic) {
				Vector newPosition = new Vector(object.position.x, object.position.y);
				object.physics.isGrounded = false;
				// Velocity and Gravity
				if (object.physics.doGravity) {
					object.physics.velocity.y -= 0.002;
				}
				newPosition.x += object.physics.velocity.x;
				newPosition.y += object.physics.velocity.y;
				// Collisions
				if (object.physics.doCollisions) {
					for (GameObject other : objects) {
						if (object != other && other.physics.doCollisions) {
							// Vertical Collisions
							if (other.position.x - object.width/2 - other.width/2 < newPosition.x && newPosition.x < other.position.x + object.width/2 + other.width/2) {
								if (other.position.y - object.height/2 - other.height/2 < newPosition.y && newPosition.y < other.position.y + object.height/2 + other.height/2) {
									object.physics.velocity.y = 0;
									if (object.position.y > other.position.y) {
										newPosition.y = other.position.y + other.height/2 + object.height/2;
										object.physics.isGrounded = true;
									} else {
										newPosition.y = other.position.y - other.height/2 - object.height/2;
									}
								}
							}
						}
					}
				}
				object.position = newPosition;
			}
		}
	}
}
