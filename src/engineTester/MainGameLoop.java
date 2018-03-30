 package engineTester;

import org.lwjgl.opengl.Display;

import RenderEngine.DisplayManager;
import RenderEngine.Loader;
import RenderEngine.Renderer;
import models.RawModel;
import models.TexturedModel;
import shaders.StaticShader;
import textures.ModelTexture;

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
		
		float[] textureCoords= {
				0,0,	//v0
				0,1,	//v1
				1,1,	//v2
				1,0		//v3
		};

		RawModel model= loader.loadtoVAO(vertices,textureCoords,indices);
		ModelTexture texture=new ModelTexture(loader.loadTexture("Image"));
		TexturedModel texturedModel=new TexturedModel(model,texture);
		
		while(!Display.isCloseRequested()) {
			renderer.prepare();
			shader.start();
			//game logic
			renderer.render(texturedModel);
			shader.stop();
			DisplayManager.updateDisplay();
			
		}
		
		shader.cleanUp();
		loader.CLeamUp();
		DisplayManager.closeDisplay();

	}

}
