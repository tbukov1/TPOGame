package tpo.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import elements.Player;

public class TPOGame extends ApplicationAdapter implements InputProcessor {

	SpriteBatch spriteBatch;
	TextureRegion currentFrame;
	Player player;

	float stateTime;

	@Override
	public void create() {
		spriteBatch = new SpriteBatch();
		player = new Player(100, 100, "data/player/sprites_player_3.png");
		stateTime = 0f;
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		stateTime += Gdx.graphics.getDeltaTime();
		currentFrame = player.currentFrame(stateTime);
		spriteBatch.begin();
		spriteBatch.draw(currentFrame, 90, 90);
		spriteBatch.end();
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

	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Input.Keys.LEFT)
			player.left();
		if (keycode == Input.Keys.RIGHT)
			player.right();
		if (keycode == Input.Keys.UP)
			player.up();
		if (keycode == Input.Keys.DOWN)
			player.down();
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
