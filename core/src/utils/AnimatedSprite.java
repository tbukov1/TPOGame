package utils;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class AnimatedSprite{
	public MyAnimation animation;
	float width, height, scaleX,scaleY;
	Body body;
	
	public AnimatedSprite(Body body,float scaleX, float scaleY){
		this.body = body;
		animation = new MyAnimation();
		this.scaleX = scaleX;
		this.scaleY = scaleY;
	}
	
	public void setAnimation(TextureRegion[] reg, float df){
		animation.setFrame(reg, df);
		width = reg[0].getRegionWidth()*scaleX;
		height = reg[0].getRegionHeight()*scaleY;
	}
	
	public void update(float df){
		animation.update(df);
	}
	
	public void render(SpriteBatch sb){
		sb.setProjectionMatrix(Camera.getCamera().combined);
		sb.begin();
		sb.draw(animation.getFrame(), body.getPosition().x - width/2, body.getPosition().y - height/2,width, height);
		
		sb.end();
	}
	
	public Vector2 getPosition(){
		return body.getPosition();
	}
	
}
