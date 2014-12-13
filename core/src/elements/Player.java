package elements;

import utils.AnimatedSprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

public class Player extends AnimatedSprite{

	private static final int FRAME_COLS = 4;
	private static final int FRAME_ROWS = 4;
	/*
	 * dol je 0, levo je 1, gor je 2, desno je 3
	 */
	private Texture walkSheet;
	private TextureRegion[] walkFrames;
	private TextureRegion[][] tmp;
	public AnimatedSprite sprite;

	public Player(Body body,String potTex) {		
		super(body);
		walkSheet = new Texture(Gdx.files.internal(potTex));
		tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / FRAME_COLS,
				walkSheet.getHeight() / FRAME_ROWS);
		walkFrames = new TextureRegion[1];
		walkFrames[0] = tmp[0][0];
		setAnimation(walkFrames, 1/12f);
	}
	
	public void move(int dir){
		int in = 0;
		walkFrames = new TextureRegion[FRAME_COLS];
		for (int i = 0; i < walkFrames.length; i++) {
			walkFrames[in++] = tmp[dir][i];
		}
		movePos(dir);
		setAnimation(walkFrames,1/12f);
		
		
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
		TextureRegion tmp = walkFrames[0];
		walkFrames = new TextureRegion[1];
		walkFrames[0] = tmp;
		setAnimation(walkFrames,1/12f);
	}
}
