package elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Player extends Sprite {

	private static final int FRAME_COLS = 4;
	private static final int FRAME_ROWS = 4;
	/*
	 * dol je 0, levo je 1, gor je 2, desno je 3
	 */
	private int x, y;
	private Animation walkAnimation;
	private Texture walkSheet;
	private TextureRegion[] walkFrames;
	private TextureRegion[][] tmp;

	public Player(int x, int y, String potTex) {
		super(new Texture(Gdx.files.internal(potTex)), x, y);
		walkSheet = new Texture(Gdx.files.internal(potTex));
		tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / FRAME_COLS,
				walkSheet.getHeight() / FRAME_ROWS);
		/*
		 * walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS]; int index =
		 * 0; for (int i = 0; i < FRAME_ROWS; i++) { for (int j = 0; j <
		 * FRAME_COLS; j++) { walkFrames[index++] = tmp[i][j]; } }
		 */
	}

	public TextureRegion currentFrame(float stateTime) {
		return walkAnimation.getKeyFrame(stateTime, true);
	}

	public void down() {
		int in = 0;
		walkFrames = new TextureRegion[FRAME_COLS];
		for (int i = 0; i < walkFrames.length; i++) {
			walkFrames[in++] = tmp[0][i];
			System.out.println(tmp[0][i]);
		}
		walkAnimation = new Animation(0.15f, walkFrames);
	}

	public void left() {
		int in = 0;
		walkFrames = new TextureRegion[FRAME_COLS];
		for (int i = 0; i < walkFrames.length; i++) {
			walkFrames[in++] = tmp[1][i];
			System.out.println(tmp[1][i]);
		}
		walkAnimation = new Animation(0.15f, walkFrames);
	}

	public void up() {
		int in = 0;
		walkFrames = new TextureRegion[FRAME_COLS];
		for (int i = 0; i < walkFrames.length; i++) {
			walkFrames[in++] = tmp[2][i];
			System.out.println(tmp[2][i]);
		}
		walkAnimation = new Animation(0.15f, walkFrames);
	}

	public void right() {
		int in = 0;
		walkFrames = new TextureRegion[FRAME_COLS];
		for (int i = 0; i < walkFrames.length; i++) {
			walkFrames[in++] = tmp[3][i];
			System.out.println(tmp[3][i]);
		}
		walkAnimation = new Animation(0.15f, walkFrames);
	}
}
