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
		
		Player player = new Player(CyborgModel, new Vector3f(400,0,-400),0,0,0,0.5f);
		player.increaseRotation(0, 180, 0);


		Terrain terrain = new Terrain(0,-1,loader,new ModelTexture(loader.loadTexture("grass")), "heightmap");
		List<Entity> entities = new ArrayList<Entity>();
		Random random = new Random();
		for(int i=0;i<500;i++){
			float x=random.nextFloat()*800 - 400;
			float z=random.nextFloat() * -800;
			float y=terrain.getHeightOfTerrain(x,z);
			entities.add(new Entity(staticModel, new Vector3f(x,y,z),0,0,0,3));
		}
		
		Light light = new Light(new Vector3f(20000,20000,2000),new Vector3f(1,1,1));
		
		Camera camera = new Camera(player);	
		MasterRenderer renderer = new MasterRenderer();
		
		
		while(!Display.isCloseRequested()){
			camera.move();
			player.move(terrain);
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
