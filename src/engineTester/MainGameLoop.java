 package engineTester;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import RenderEngine.DisplayManager;
import RenderEngine.Loader;
import RenderEngine.MasterRenderer;
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
		MasterRenderer renderer = new MasterRenderer();

		RawModel model= OBJLoader.loadObjModel("dragon", loader);
		
		TexturedModel texturedModel=new TexturedModel(model,new ModelTexture(loader.loadTexture("Yellow")));
		ModelTexture texture= texturedModel.getTexture();
		texture.setReflectivity(1);
		texture.setShineDamper(10);
		Entity entity =new Entity(texturedModel, new Vector3f(0,-8,-12),0,0,0,1);
		Light light = new Light(new Vector3f(10,0,0),new Vector3f(1,1,1));
		
		Camera camera= new Camera();
		
		
		
		while(!Display.isCloseRequested()) {
			entity.increasePosition(0, 0, 0);
			entity.increaseRotation(0, 1, 0);
			camera.move();
			
			renderer.processEntity(entity); // do this for all the entities in model
			
			renderer.render(light, camera); 
			DisplayManager.updateDisplay();
			
		}
		
		renderer.cleanUp();
		loader.CLeamUp();
		DisplayManager.closeDisplay();

	}

}
