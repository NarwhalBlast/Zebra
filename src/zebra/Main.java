package zebra;

import java.awt.Dimension;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;

public class Main {
	
	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Zebra");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1280, 720);
		frame.setMaximumSize(new Dimension(1280, 720));
		frame.setVisible(true);
		
		Screen screen = new Screen();
		Game game = new Game();
		
		frame.add(screen);
		
		while (true) {
			long startTime = System.currentTimeMillis();
			
			game.Update();
			game.drawToScreen(screen);
			
			long endTime = System.currentTimeMillis();
			
			int deltaTime = (int)(endTime - startTime);
			
			TimeUnit.MICROSECONDS.sleep(1667 - deltaTime);
		}
	}
}