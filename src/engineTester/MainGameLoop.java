 package engineTester;

import org.lwjgl.opengl.Display;

import RenderEngine.DisplayManager;

public class MainGameLoop {

	public static void main(String[] args) {

		DisplayManager.createDisplay();
		
		while(!Display.isCloseRequested()) {
			
			//game logic
			// Rendering
			DisplayManager.updateDisplay();
			
		}
		
		DisplayManager.closeDisplay();

	}

}
