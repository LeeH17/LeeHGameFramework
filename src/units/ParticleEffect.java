package units;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;

/**
 * Small class for drawing particle effects
 * 
 * @author HarrisonLee
 */
public class ParticleEffect {
	//The shape and color of this specific particle effect
	Shape shape;
	Color color;
	int duration;
	int id;			//The index id of this particle in sView
	
	public ParticleEffect(Shape newShape, Color newColor,
			int newDuration){
		shape = newShape;
		color = newColor;
		duration = newDuration;
	}
	
	/**
	 * Use graphics2D to draw this particle effect,
	 * based off of the given shape and color
	 * @param g
	 */
	public void paint(Graphics2D g){
		g.setColor(color);
		g.draw(shape);
	}
	
	/**
	 * When duration <= 0, this will no longer be drawn
	 * @param deltaTime: The time that has passed
	 */
	public void reduceDuration(int deltaTime){
		duration -= deltaTime;
	}
	public int getDuration()	{ return duration;	}
	public int getId()			{ return id;		}
	public void setId(int newId){ id = newId;		}
}
