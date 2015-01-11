package screens;

import java.util.ArrayList;

import tpo.game.TPOGame2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class FinalScreen implements Screen{
	
	private Stage stage;
//	OrthographicCamera cam;
	Skin skin;
	String[] buttons;
	ArrayList<TextButton> buttonArray;
	
	BitmapFont font;
	BitmapFont font1;
	SpriteBatch sb;
	float scale = 0.6f, scale1 = 0.4f;

	TPOGame2 game;	
	public FinalScreen(final TPOGame2 game){
		
		
		
		this.game = game;
		stage = new Stage();
		skin = new Skin();
		sb = new SpriteBatch();
		
		font = new BitmapFont(Gdx.files.internal("data/fonts/CustomFont.fnt"));
		if(Gdx.graphics.getWidth()!= 800)
			scale = 2f;	
		font.setScale(scale);
		font.setColor(232, 32, 23,1);		
		
		font1 = new BitmapFont(Gdx.files.internal("data/fonts/CustomFont.fnt"));
		if(Gdx.graphics.getWidth()!= 800)
			scale1 = 1.5f;	
		font1.setScale(scale1);
		font1.setColor(1, 0.51f, 0.26f,1);
		
		buttons = new String[] { "Ponovi?", "Izhod" };
		buttonArray = new ArrayList<TextButton>();

		Pixmap pixmap = new Pixmap(100, 100, Format.RGBA8888);
		pixmap.setColor(Color.CLEAR);
		pixmap.fill();
		skin.add("clear", new Texture(pixmap));

		pixmap.setColor(Color.BLUE);
		pixmap.fill();

		skin.add("blue", new Texture(pixmap));
		BitmapFont font = new BitmapFont(Gdx.files.internal("data/fonts/CustomFont32.fnt"));
		font.scale(Gdx.graphics.getWidth() != 800? 5f : 0.9f);
		skin.add("default", font);

		TextButtonStyle tBSyle = new TextButtonStyle();
		tBSyle.up = skin.newDrawable("clear", Color.DARK_GRAY);
		tBSyle.down = skin.newDrawable("blue", Color.DARK_GRAY);
		tBSyle.checked = skin.newDrawable("clear", Color.BLUE);
		tBSyle.over = skin.newDrawable("blue", Color.BLUE);

		tBSyle.font = skin.getFont("default");
		skin.add("default", tBSyle);
		TextButton prev = null;
		float b = -30;
		for (int i = 0; i < buttons.length; i++) {
			TextButton tmp = new TextButton(buttons[i], tBSyle);
			if (prev != null)
				b += prev.getWidth();
			tmp.setPosition(Gdx.graphics.getWidth() / 2 - tmp.getWidth() +b,
					Gdx.graphics.getHeight() / 2 - tmp.getHeight());
			stage.addActor(tmp);
			buttonArray.add(tmp);
			prev = tmp;
		}

	}

	@Override
	public void show() {
		
		Gdx.input.setInputProcessor(stage);
		for (TextButton button : buttonArray) {
			final String tex = button.getText().toString();
			button.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					// TODO Auto-generated method stub
					if (tex.equals("Ponovi?")) {
						System.out.println("Open game!");
						game.fh.delete();
						game.points = 0;
						game.stage = 0;
						game.gameScreen = new GameScreen(game);
						game.setScreen(game.gameScreen);
						Gdx.input.setInputProcessor(null);
					}
					if (tex.equals("Izhod")) {
						System.out.println("Close game!");
						game.fh.delete();
						Gdx.app.exit();
					}
				}
			});
		}
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub

		float w = font.getBounds("Zmagali ste!").width / 2;
		String text = "Dosegli ste "+game.points+" toèk!";
		float w2 = font1.getBounds(text).width / 2;
		float h = font.getBounds("Zmagali ste!").height;
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sb.begin();
		font.drawMultiLine(sb, "Zmagali ste!", Gdx.graphics.getWidth() / 2 - w,
				Gdx.graphics.getHeight() - (h));
		font1.draw(sb, text, Gdx.graphics.getWidth() / 2 -w2, Gdx.graphics.getHeight() -(h*3));
		sb.end();
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		stage.dispose();
		skin.dispose();
	}

}
