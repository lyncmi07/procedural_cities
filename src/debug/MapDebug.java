package debug;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import maps.Map;

public class MapDebug {
	public static void printMapToScreen(Map map)
	{
		
		final BufferedImage finalImage = map.getMap();
		
		System.out.println(map.getMap());
		
		JFrame frame = buildFrame(map.getMap().getWidth(), map.getMap().getHeight());
		
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
