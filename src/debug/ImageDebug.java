package debug;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import maps.Map;

public class ImageDebug {
	/**
	 * Prints an image to the screen
	 * @param image image to be printed to screen
	 */
	public static void printImageToScreen(BufferedImage image)
	{
		
		final BufferedImage finalImage = image;
		
		//System.out.println(map.getMap());
		
		JFrame frame = buildFrame(image.getWidth(), image.getHeight());
		
		JPanel pane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(finalImage, 0, 0, null);
                
            }
        };
		
		frame.add(pane);
	}
	
	private static JFrame buildFrame(int width, int height) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setVisible(true);
        return frame;
    }
}
