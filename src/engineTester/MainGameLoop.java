 package engineTester;

import org.lwjgl.opengl.Display;

import RenderEngine.DisplayManager;
import RenderEngine.Loader;
import RenderEngine.RawModel;
import RenderEngine.Renderer;
import shaders.StaticShader;

public class MainGameLoop {

	public static void main(String[] args) {

		DisplayManager.createDisplay();
		Loader loader= new Loader();
		Renderer renderer= new Renderer();
		StaticShader shader= new StaticShader();
		
		float[] vertices = {
				-0.5f, 0.5f, 0f,	//v0
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
			shader.start();
			//game logic
			renderer.render(model);
			shader.stop();
			DisplayManager.updateDisplay();
			
		}
		
		shader.cleanup();
		loader.CLeamUp();
		DisplayManager.closeDisplay();

	}

}
