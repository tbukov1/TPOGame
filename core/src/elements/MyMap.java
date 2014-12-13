package elements;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class MyMap extends ApplicationAdapter implements InputProcessor {

	public static final String title = "TPO game";
	
	public static final int width = 800;
	public static final int height = 400;
	public static final float moveUnit = 8.0f;

<<<<<<< HEAD:core/src/elements/MyMap.java
	Texture img;
	public TiledMap tiledMap;
=======
	TiledMap tiledMap;
>>>>>>> origin/master:core/src/elements/Map.java
	OrthographicCamera camera;
	TiledMapRenderer tiledMapRenderer;
	float camXmoved, camYmoved;

	public MyMap(String filename){
		this.create(filename);
	}
	
	@Override
	public void create() {
		this.create("data/map/testMapa.tmx");
	}
	
	private void create(String filename){
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camXmoved = 0;
		camYmoved = 0;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, w, h);
		camera.update();
		tiledMap = new TmxMapLoader().load(filename);
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();
	}
	public void moveCamera(int direction){
		if (direction == 1){
			if (camXmoved >= -264){
				camera.translate(-moveUnit, 0);
				camXmoved -= moveUnit;
			}
			System.out.println(camXmoved);
		}
		if (direction == 3){
			if (camXmoved <= 664){
				camera.translate(moveUnit, 0);
				camXmoved += moveUnit;
			}
			System.out.println(camXmoved);
		}
		if (direction == 0){
			if (camYmoved >= -168){
				camera.translate(0, -moveUnit);
				camYmoved -= moveUnit;
			}
			System.out.println(camYmoved);
		}
		if (direction == 2){
			if (camYmoved <= 680){
				camera.translate(0, moveUnit);
				camYmoved += moveUnit;
			}
			System.out.println(camYmoved);
		}
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Input.Keys.LEFT)
			camera.translate(-32, 0);
		if (keycode == Input.Keys.RIGHT)
			camera.translate(32, 0);
		if (keycode == Input.Keys.UP)
			camera.translate(0, -32);
		if (keycode == Input.Keys.DOWN)
			camera.translate(0, 32);
		if (keycode == Input.Keys.NUM_1)
			tiledMap.getLayers().get(0)
					.setVisible(!tiledMap.getLayers().get(0).isVisible());
		if (keycode == Input.Keys.NUM_2)
			tiledMap.getLayers().get(1)
					.setVisible(!tiledMap.getLayers().get(1).isVisible());
		return false;
	}

	@Override
	public boolean keyTyped(char character) {

		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}