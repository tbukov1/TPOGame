package tpo.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class AnimatedSprite extends Sprite {
	private Animation animation;
	private float time;
	private boolean playing = true;

	public AnimatedSprite(Animation animation) {
		super(animation.getKeyFrame(0,true));
		this.animation = animation;
	}

	public void update() {
		update(Gdx.graphics.getDeltaTime());
	}

	private void update(float deltaTime) {
		if (playing) {
			time += deltaTime;
			setRegion(animation.getKeyFrame(time,true));
		}
	}

	@Override
	public void draw(Batch batch) {
		update();
		super.draw(batch);
	}

	public void play() {
		playing = true;
	}

	public void pause() {
		playing = false;
	}

	public void stop() {
		playing = false;
		time = 0;
	}

	public Animation getAnimation() {
		return animation;
	}

	public void setAnimation(Animation animation) {
		time = 0;
		this.animation = animation;
	}

	public float getTime() {
		return time;
	}

	public void setTime(float time) {
		this.time = time;
	}

	public boolean isPlaying() {
		return playing;
	}

	public void setPlaying(boolean playing) {
		this.playing = playing;
	}
}
