package tpo.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import elements.Player;

public class TPOGame extends ApplicationAdapter {
	SpriteBatch spriteBatch;
	TextureRegion currentFrame;
	Player proba;

	float stateTime;

	@Override
	public void create() {
		spriteBatch = new SpriteBatch();
		proba = new Player(100, 100, "data/player/sprites_player_3.png");
		stateTime = 0f;
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		stateTime += Gdx.graphics.getDeltaTime();
		currentFrame = proba.currentFrame(stateTime);
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
}
