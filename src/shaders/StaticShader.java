 	package shaders;

import org.lwjgl.util.vector.Matrix4f;

import entities.Camera;
import toolBox.Maths;

public class StaticShader extends ShaderProgram{
	
	private int location_transformationMatrix;
	private int location_projectionMatrix;
	private int location_viewMatrix;
	

	private static final String VERTEX_FILE="src/shaders/vertexShader.txt";
	private static final String FRAGMENT_FILE="src/shaders/fragmentShader.txt";
	
	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);		
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords"); 
	}

	protected void getAllUniformLocations() {
		location_transformationMatrix=super.getUniformLocation("transformationMatrix");
		location_projectionMatrix=super.getUniformLocation("projectionMatrix");
		location_viewMatrix = super.getUniformLocation("viewMatrix");
	}
	
	public void loadViewMatrix(Camera camera) {
		Matrix4f viewMatrix= Maths.createViewMatrix(camera);
		super.loadMatrix(location_viewMatrix, viewMatrix);
	}
	
	public void loadTransformationMatrix(Matrix4f matrix) {
		super.loadMatrix(location_transformationMatrix, matrix);
	}
	
	public void loadProjectionMatrix(Matrix4f projection) {
		super.loadMatrix(location_projectionMatrix, projection);
	}
}
