package utils;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class Camera {
	private OrthographicCamera camera;
	private static Camera cam;

	private Camera() {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Constants.APP_WIDTH, Constants.APP_HEIGHT);
		camera.update();
	}

	private Camera(OrthographicCamera cam) {
		camera = cam;
	}

	public static OrthographicCamera getCamera() {
		if (cam == null)
			cam = new Camera();
		return cam.camera;
	}
}
