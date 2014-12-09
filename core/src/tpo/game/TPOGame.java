package tpo.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TPOGame extends ApplicationAdapter {
	SpriteBatch batch;
	BitmapFont font;
	Sprite sprite;
	Texture texture;
	
	@Override
	public void create () {
        batch = new SpriteBatch();    
        font = new BitmapFont();
        font.setColor(Color.RED);
        texture = new Texture(Gdx.files.internal("data/jet.png"));
        sprite = new Sprite(texture);
	}

	@Override
	public void render () {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.begin();
        font.draw(batch, "NAJHUJSI SPIL EVER!!!", 200, 200);
        sprite.draw(batch);
        batch.end();
	}
    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
