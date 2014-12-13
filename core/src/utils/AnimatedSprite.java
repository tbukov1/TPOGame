package utils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class AnimatedSprite{
	MyAnimation animation;
	float width, height;
	Body body;
	
	public AnimatedSprite(Body body){
		this.body = body;
		animation = new MyAnimation();
	}
	
	public void setAnimation(TextureRegion[] reg, float df){
		animation.setFrame(reg, df);
		width = reg[0].getRegionWidth();
		height = reg[0].getRegionHeight();
	}
	
	public void update(float df){
		animation.update(df);
	}
	
	public void render(SpriteBatch sb){
		sb.begin();
		sb.draw(animation.getFrame(), body.getPosition().x - width/2, body.getPosition().y - height/2);
		sb.end();
	}
	
	public Vector2 getPosition(){
		return body.getPosition();
	}
	
}
