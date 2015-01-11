package elements;

import question_parser.Question;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

public class Monster {
	private TextureRegion tr;
	public Question question;
	public Body body;
	float width, height, scaleX,scaleY;
	
	public Monster(Body body, String path, Question question, float scaleX, float scaleY){
		Texture tex = new Texture(Gdx.files.internal(path));
		tr = new TextureRegion(tex);
		this.question = question;
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.body = body;
		width = tr.getRegionWidth()*scaleX;
		height = tr.getRegionHeight()*scaleY;
	}
	
	public void render(SpriteBatch sb){
		sb.begin();
		sb.draw(tr, body.getPosition().x - width/2, body.getPosition().y - height/2,width, height);		
		sb.end();
	}
}
