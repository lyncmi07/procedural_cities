package display;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

public class DisplayManager {
	public static void createDisplay()
	{
		ContextAttribs attribs = new ContextAttribs(3,2)
		.withForwardCompatible(true)
		.withProfileCore(true);
		try
		{
			Display.setDisplayMode(new org.lwjgl.opengl.DisplayMode(1200, 800));
			//Display.create(new PixelFormat(), attribs);	//windows
			Display.create(new PixelFormat());
			Display.setTitle("Procedural Cities");
		}
		catch(LWJGLException e)
		{
			e.printStackTrace();
		}
		
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glViewport(0, 0, 256, 256);
	}
	
	public static void updateDisplay()
	{
		Display.sync(120);
		Display.update();
	}
	
	public static void closeDisplay()
	{
		Display.destroy();
	}
}
