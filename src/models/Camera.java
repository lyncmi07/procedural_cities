package models;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	private Vector3f position = new Vector3f(0,0,0);
	private Vector3f rotation = new Vector3f(0,0,0);
	
	public Camera(Vector3f position, Vector3f rotation)
	{
		this.position = position;
		this.rotation = rotation;
	}
	
	public void move()
	{
		if(Keyboard.isKeyDown(Keyboard.KEY_W))
		{
			position.z -= 1;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D))
		{
			position.x += 1;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A))
		{
			position.x -= 1;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S))
		{
			position.z += 1;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_Q))
		{
			rotation.y -= 1;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_E))
		{
			rotation.y += 1;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_Z))
		{
			position.y += 1;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_X))
		{
			position.y -= 1;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN))
		{
			rotation.z -= 1;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_UP))
		{
			rotation.z += 1;
		}
	}
	
	public void setPosition(float dx, float dy, float dz)
	{
		position.x = dx;
		position.y = dy;
		position.z = dz;
	}
	
	public Vector3f getPosition()
	{
		return position;
	}
	public float getPitch()
	{
		return rotation.x;
	}
	public float getYaw()
	{
		return rotation.y;
	}
	public float getRoll()
	{
		return rotation.z;
	}
}
