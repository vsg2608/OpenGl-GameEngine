 package engineTester;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import RenderEngine.DisplayManager;
import RenderEngine.Loader;
import RenderEngine.OBJLoader;
import RenderEngine.Renderer;
import entities.Camera;
import entities.Entity;
import entities.Light;
import models.RawModel;
import models.TexturedModel;
import shaders.StaticShader;
import textures.ModelTexture;

public class MainGameLoop {

	public static void main(String[] args) {

		DisplayManager.createDisplay();
		Loader loader= new Loader();
		StaticShader shader= new StaticShader();
		Renderer renderer= new Renderer(shader);
		
		
		RawModel model= OBJLoader.loadObjModel("Cyborg", loader);
		
		ModelTexture texture=new ModelTexture(loader.loadTexture("White"));
		TexturedModel texturedModel=new TexturedModel(model,texture);
		
		Entity entity =new Entity(texturedModel, new Vector3f(0,-5,-10),0,0,0,1);
		Light light = new Light(new Vector3f(0,0,0),new Vector3f(1,1,1));
		
		Camera camera= new Camera();
		
		while(!Display.isCloseRequested()) {
			entity.increasePosition(0, 0, 0);
			entity.increaseRotation(0, 1, 0);
			camera.move();
			renderer.prepare();
			shader.start();
			shader.loadLight(light);
			shader.loadViewMatrix(camera);
			//game logic
			renderer.render(entity, shader);
			shader.stop();
			DisplayManager.updateDisplay();
			
		}
		
		shader.cleanUp();
		loader.CLeamUp();
		DisplayManager.closeDisplay();

	}

}
