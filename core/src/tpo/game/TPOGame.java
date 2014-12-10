package tpo.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import elements.Map;
import elements.Player;

public class TPOGame extends ApplicationAdapter implements InputProcessor {

	SpriteBatch spriteBatch;
	Player player;
	Map map; 
	public float width ,height;
	World world;
	
	
	@Override
	public void create() {
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		
		spriteBatch = new SpriteBatch();
		map = new Map("data/map/testMapa.tmx");
		world = new World(new Vector2(0, 0), true);
		player = new Player("data/player/sprites_player_3.png", this);
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		
		processInput();
		map.render(); 
		spriteBatch.begin();
		player.render(spriteBatch);
		
		spriteBatch.end();
	}
	
	private void processInput(){
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
			player.move(1);
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
			player.move(3);
		if (Gdx.input.isKeyPressed(Input.Keys.UP))
			player.move(2);
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
			player.move(0);
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
