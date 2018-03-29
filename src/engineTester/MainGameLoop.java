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
		
		//OpenGL expects vertices to be defined counter clockwise by default
		 float[] vertices = {
			    //First Triangle
				-0.5f, 0.5f, 0f,
			    -0.5f, -0.5f, 0f,
			    0.5f, -0.5f, 0f,
			    //Second triangle
			    0.5f, -0.5f, 0f,
			    0.5f, 0.5f, 0f,
			    -0.5f, 0.5f, 0f
			  };

		RawModel model= loader.loadtoVAO(vertices);
		
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
