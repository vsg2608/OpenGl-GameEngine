 package engineTester;

import org.lwjgl.opengl.Display;

import RenderEngine.DisplayManager;
import RenderEngine.Loader;
import RenderEngine.RawModel;
import RenderEngine.Renderer;

public class MainGameLoop {

	public static void main(String[] args) {

		DisplayManager.createDisplay();
		
		Loader loader= new Loader();
		Renderer renderer= new Renderer();
		
		float[] vertices = {
				-0.5f, 0.7f, 0f,	//v0
			    -0.5f, -0.5f, 0f,	//v1
			    0.5f, -0.5f, 0f,	//v2
			    0.5f, 0.5f, 0f		//v3
			  };
		
		int[] indices = {
				0,1,3,
				3,1,2
		};

		RawModel model= loader.loadtoVAO(vertices, indices);
		
		while(!Display.isCloseRequested()) {
			renderer.prepare();
			
			//game logic
			renderer.render(model);
			DisplayManager.updateDisplay();
			
		}
		
		loader.CLeamUp();
		DisplayManager.closeDisplay();

	}

}
