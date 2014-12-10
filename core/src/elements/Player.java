package elements;

import tpo.game.AnimatedSprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player {

	private static final int FRAME_COLS = 4;
	private static final int FRAME_ROWS = 4;
	/*
	 * dol je 0, levo je 1, gor je 2, desno je 3
	 */
	private Animation walkAnimation;
	private Texture walkSheet;
	private TextureRegion[] walkFrames;
	private TextureRegion[][] tmp;
	private AnimatedSprite sprite;

	public Player(String potTex) {
		walkSheet = new Texture(Gdx.files.internal(potTex));
		tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / FRAME_COLS,
				walkSheet.getHeight() / FRAME_ROWS);
		/*
		 * walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS]; int index =
		 * 0; for (int i = 0; i < FRAME_ROWS; i++) { for (int j = 0; j <
		 * FRAME_COLS; j++) { walkFrames[index++] = tmp[i][j]; } }
		 */
		walkFrames = new TextureRegion[1];
		walkFrames[0] = tmp[0][0];
		walkAnimation = new Animation(0.15f, walkFrames);
		sprite = new AnimatedSprite(walkAnimation);
	}
	
	public void move(int dir){
		int in = 0;
		walkFrames = new TextureRegion[FRAME_COLS];
		for (int i = 0; i < walkFrames.length; i++) {
			walkFrames[in++] = tmp[dir][i];
		}
		walkAnimation = new Animation(0.15f, walkFrames);
		sprite.setAnimation(walkAnimation);
	}

	public void stop() {
		walkAnimation = new Animation(0.15f, walkFrames[0]);
		sprite.setAnimation(walkAnimation);
	}
	public void draw(Batch b){
		sprite.draw(b);
	}
	public void setPosition(float x, float y){
		sprite.setPosition(x, y);
	}
	public void setScale(float xy){
		sprite.setScale(xy);
	}
	public float getX(){return sprite.getX();}
	public float getY(){return sprite.getY();}
}
