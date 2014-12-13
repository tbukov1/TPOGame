package tpo.game;

import utils.MapBodyManager;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import elements.MyMap;
import elements.Player;

public class TPOGame extends ApplicationAdapter implements InputProcessor {
	public static final float STEP = 1 / 60f;
	
	SpriteBatch spriteBatch;
	Player player;
	MyMap map; 
	public float width ,height;
	World world;
	MapBodyManager mapBodyManager;
	
	
	@Override
	public void create() {
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		
		spriteBatch = new SpriteBatch();

		map = new MyMap("data/map/testMapa.tmx");
		world = new World(new Vector2(0, 0), true);
//		player = new Player("data/player/sprites_player_3.png", this);
	    mapBodyManager = new MapBodyManager(world, 1.0f, Gdx.files.internal("data/map/materials.json"), 0);
		mapBodyManager.createPhysics(map.tiledMap, "okvir");
		
		

		
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		
		mapBodyManager.createPhysics(map.tiledMap, "okvir");
		
		int movement = processInput();
//		map.render();
//		map.moveCamera(movement);
		
		BodyDef bdef = new BodyDef();
//		bdef.position.x = player.x;
//		bdef.position.y = player.y;
//		Body body = world.createBody(bdef);
//		body.createFixture(player.shape, 0f);
//		
		spriteBatch.begin();
		player.render(spriteBatch);
		spriteBatch.end();
		world.step(STEP, 1, 1);
		
	}
	
	private int processInput(){
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			player.move(1);
			return 1;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			player.move(3);
			return 3;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)){
			player.move(2);
			return 2;
		}	
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			player.move(0);
			return 0;
		}
		return -1;
			
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		mapBodyManager.destroyPhysics();
		spriteBatch.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Input.Keys.LEFT)
			player.move(1);
		if (keycode == Input.Keys.RIGHT)
			player.move(3);
		if (keycode == Input.Keys.UP)
			player.move(2);
		if (keycode == Input.Keys.DOWN)
			player.move(0);
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Input.Keys.LEFT)
		player.stop();
	if (keycode == Input.Keys.RIGHT)
		player.stop();
	if (keycode == Input.Keys.UP)
		player.stop();
	if (keycode == Input.Keys.DOWN)
		player.stop();
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
