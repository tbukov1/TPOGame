package elements;

import utils.AnimatedSprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Player extends AnimatedSprite{

	private static final int FRAME_COLS = 4;
	private static final int FRAME_ROWS = 4;
	public static final float moveUnit = 3f;
	/*
	 * dol je 0, levo je 1, gor je 2, desno je 3
	 */
	private Texture walkSheet;
	private TextureRegion[] walkFrames;
	private TextureRegion[][] tmp;
	Body body;
	private boolean moving;

	public Player(Body body,String potTex, float scaleX, float scaleY ) {		
		super(body,scaleX, scaleY);
		this.body = body;
		walkSheet = new Texture(Gdx.files.internal(potTex));
		tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / FRAME_COLS,
				walkSheet.getHeight() / FRAME_ROWS);
		walkFrames = new TextureRegion[1];
		walkFrames[0] = tmp[0][0];
		setAnimation(walkFrames, 1/12f);
		moving = false;
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
		//if(!moving){
			//Vector2 pos = body.getPosition();
//			float x = pos.x;
//			float y = pos.y;
			float x,y;
			x = y = 0;
			switch(dir){
				case 0: y = -moveUnit*100; break;
				case 1: x = -moveUnit*100; break;
				case 2: y = moveUnit*100; break;
				case 3: x = moveUnit*100; break;
			}
			body.setLinearVelocity(new Vector2(x,y));
			//body.applyForce(new Vector2(x,y), body.getWorldCenter(), true);;
			moving = true;
//			body.setTransform(new Vector2(x, y), body.getAngle());
		//}
		
	}

	public void stop() {
		TextureRegion tmp = walkFrames[0];
		walkFrames = new TextureRegion[1];
		walkFrames[0] = tmp;
		animation.currentFrame = 0;
		setAnimation(walkFrames,1/12f);
		body.setLinearVelocity(new Vector2(0,0));
		moving = false;
		//body.applyForce(body.getLinearVelocity().scl(-1.0f), body.getWorldCenter(), true);
		//body.setTransform(body.getPosition(), body.getAngle());
	}
}
