package stages;

import java.util.ArrayList;

import tpo.game.TPOGame2;
import utils.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class QuestionStage extends Stage implements InputProcessor{
	// za prikazovanje vpra�anj in klik na gumbe. �e je rezultat praviln shran� nekam
	
	public String question;
	public String[] answers;
	public int correctAnswer;
	public float margin,offsetL, offsetT;
	TPOGame2 game;	
	

	private ArrayList<TextButton> buttonArray;
	Skin skin;
	
	BitmapFont font;
	SpriteBatch sb;
	
	public QuestionStage(TPOGame2 game	){
		font = new BitmapFont(Gdx.files.internal("data/fonts/CustomFont.fnt"));
		font.setScale(0.6f);
		sb = new SpriteBatch();
		margin = 20f;
		offsetL = 70f;
		offsetT = 30f;
		this.game = game;
		
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		super.draw();
		float w = font.getBounds(question).width/2;
		float h = font.getBounds(question).height/2;
		sb.begin();
		font.drawMultiLine(sb, question, Constants.APP_WIDTH/2-w, Constants.APP_HEIGHT - (h*2));
		sb.end();
		super.draw();
	}
	
	public void setQuestion(String question){
		this.question = question;
	}

	public void setAnswers(String[] tab){
		answers = tab;
	}

	public void setCorrectAnswer(int correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	
	public void setAll(String q, String[] a, int cor){
		setQuestion(q);
		setAnswers(a);
		setCorrectAnswer(cor);
	}
	public void makeText(){
		skin = new Skin();
		buttonArray = new ArrayList<TextButton>();

		Pixmap pixmap = new Pixmap(100, 100, Format.RGBA8888);
		pixmap.setColor(Color.CLEAR);
		pixmap.fill();
		skin.add("clear", new Texture(pixmap));

		pixmap.setColor(Color.BLUE);
		pixmap.fill();

		skin.add("blue", new Texture(pixmap));
		BitmapFont font = new BitmapFont(Gdx.files.internal("data/fonts/CustomFont32.fnt"));
		font.scale(0.1f);
		skin.add("default", font);

		TextButtonStyle tBSyle = new TextButtonStyle();
		tBSyle.up = skin.newDrawable("clear", Color.DARK_GRAY);
		tBSyle.down = skin.newDrawable("blue", Color.DARK_GRAY);
		tBSyle.checked = skin.newDrawable("clear", Color.BLUE);
		tBSyle.over = skin.newDrawable("blue", Color.BLUE);

		tBSyle.font = skin.getFont("default");
		skin.add("default", tBSyle);
		TextButton prev = null;
		float b = 0;
		for (int i = 0; i < answers.length; i++) {
			TextButton tmp = new TextButton(answers[i], tBSyle);
			if (prev != null)
				b += prev.getWidth()+margin;
			tmp.setPosition(offsetL+b,
					Constants.APP_HEIGHT / 2 - offsetT);
			addActor(tmp);
			buttonArray.add(tmp);
			prev = tmp;
		}
	}
	
	public void setColor(float r, float g, float b){
		font.setColor(r, g, b, 1);
		
	}
}