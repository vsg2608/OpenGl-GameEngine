 package engineTester;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import RenderEngine.DisplayManager;
import RenderEngine.Loader;
import RenderEngine.MasterRenderer;
import RenderEngine.OBJLoader;
import RenderEngine.EntityRenderer;
import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Player;
import models.RawModel;
import models.TexturedModel;
import shaders.StaticShader;
import terrain.Terrain;
import textures.ModelTexture;

public class MainGameLoop {

	public static void main(String[] args) {

		DisplayManager.createDisplay();
		Loader loader = new Loader();
		
		
		RawModel model = OBJLoader.loadObjModel("tree", loader);
		TexturedModel staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("tree")));
		
		RawModel Cyrborgmodel = OBJLoader.loadObjModel("Cyborg", loader);
		TexturedModel CyborgModel = new TexturedModel(Cyrborgmodel,new ModelTexture(loader.loadTexture("White")));
		
		Player player = new Player(CyborgModel, new Vector3f(0,0,-10),0,0,0,0.5f);
		player.increaseRotation(0, 180, 0);

		List<Entity> entities = new ArrayList<Entity>();
		Random random = new Random();
		for(int i=0;i<500;i++){
			entities.add(new Entity(staticModel, new Vector3f(random.nextFloat()*800 - 400,0,random.nextFloat() * -600),0,0,0,3));
		}
		
		Light light = new Light(new Vector3f(20000,20000,2000),new Vector3f(1,1,1));
		Terrain terrain = new Terrain(0,0,loader,new ModelTexture(loader.loadTexture("grass")), "heightmap");
		
		Camera camera = new Camera(player);	
		MasterRenderer renderer = new MasterRenderer();
		
		
		while(!Display.isCloseRequested()){
			camera.move();
			player.move();
			renderer.processEntity(player);
			renderer.processTerrain(terrain);
			for(Entity entity:entities){
				renderer.processEntity(entity);
			}
			renderer.render(light, camera);
			DisplayManager.updateDisplay();
		}

		renderer.cleanUp();
		loader.CLeamUp();
		DisplayManager.closeDisplay();
	}
	
	

}
