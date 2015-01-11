package elements;

import utils.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Points {

	BitmapFont font;
	public float margin, offsetL, offsetT;
	int points;
	SpriteBatch sb;
	
	public Points(int points){
		font = new BitmapFont(Gdx.files.internal("data/fonts/CustomFont.fnt"));
		font.setScale(0.6f);
		sb = new SpriteBatch();
		margin = 20f;
		offsetL = 70f;
		offsetT = 30f;
		this.points = points;
	}
	
	public void render(int points){
		float w = font.getBounds(Integer.toString(points)+"p").width / 2;
		float h = font.getBounds(Integer.toString(points)+"p").height;
		changePoints(points);
		sb.begin();
		font.drawMultiLine(sb, Integer.toString(points)+"p", Gdx.graphics.getWidth() / 2 - w,
			Gdx.graphics.getHeight() - (h));
		sb.end();
	}
	
	public void changePoints(int points){
		this.points = points;
	}
}
