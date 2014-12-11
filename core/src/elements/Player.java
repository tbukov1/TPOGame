package elements;

import tpo.game.AnimatedSprite;
import tpo.game.TPOGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
	public AnimatedSprite sprite;
	private float x, y;

	public Player(String potTex) {
		walkSheet = new Texture(Gdx.files.internal(potTex));
		tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / FRAME_COLS,
				walkSheet.getHeight() / FRAME_ROWS);
		/*
		 * walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS]; int index =
		 * 0; for (int i = 0; i < FRAME_ROWS; i++) { for (int j = 0; j <
		 * FRAME_COLS; j++) { walkFrames[index++] = tmp[i][j]; } }
		 */
		this.x = 100;
		this.y = 100;
		walkFrames = new TextureRegion[1];
		walkFrames[0] = tmp[0][0];
		walkAnimation = new Animation(0.15f, walkFrames);
		sprite = new AnimatedSprite(walkAnimation);
	}
	
	
	public Player(String potTex, TPOGame game) {
		walkSheet = new Texture(Gdx.files.internal(potTex));
		tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / FRAME_COLS,
				walkSheet.getHeight() / FRAME_ROWS);
		/*
		 * walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS]; int index =
		 * 0; for (int i = 0; i < FRAME_ROWS; i++) { for (int j = 0; j <
		 * FRAME_COLS; j++) { walkFrames[index++] = tmp[i][j]; } }
		 */
		
		this.x = game.width/2;
		this.y = game.height/2;
		walkFrames = new TextureRegion[1];
		walkFrames[0] = tmp[0][0];
		walkAnimation = new Animation(0.15f, walkFrames);
		sprite = new AnimatedSprite(walkAnimation);
	}

	public void render(SpriteBatch sb){
		this.sprite.setCenter(this.x, this.y);
		this.sprite.setScale(0.25f);
		this.sprite.draw(sb);
	}
	
	public void move(int dir){
		int in = 0;
		walkFrames = new TextureRegion[FRAME_COLS];
		for (int i = 0; i < walkFrames.length; i++) {
			walkFrames[in++] = tmp[dir][i];
		}
		movePos(dir);
		walkAnimation = new Animation(0.15f, walkFrames);
		sprite.setAnimation(walkAnimation);
		
		
	}
	
	public void movePos(int dir){
	/*	switch(dir){
			case 0: this.y -= 1; break;
			case 1: this.x -= 1; break;
			case 2: this.y += 1; break;
			case 3: this.x += 1; break;
		}*/
	}

	public void stop() {
		walkAnimation = new Animation(0.15f, walkFrames[0]);
		sprite.setAnimation(walkAnimation);
	}
}
