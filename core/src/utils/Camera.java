package utils;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class Camera {
	private static OrthographicCamera camera;
	
	public Camera(){
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Constants.APP_WIDTH, Constants.APP_HEIGHT);
		camera.update();
	}
	
	public Camera(OrthographicCamera cam){
		camera = cam;
	}
	
	public static OrthographicCamera getCamera(){
		return camera;
	}
}
