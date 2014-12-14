package screens;

import java.util.ArrayList;

import tpo.game.TPOGame2;
import utils.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainMenu implements Screen {
	private Stage stage;
	OrthographicCamera cam;
	Skin skin;
	SpriteBatch sb;
	String[] buttons;
	ArrayList<TextButton> buttonArray;

	TPOGame2 game;

	public MainMenu(final TPOGame2 game) {
		this.game = game;
		stage = new Stage();
		cam = new OrthographicCamera(Constants.APP_WIDTH, Constants.APP_HEIGHT);
		cam.translate(Constants.APP_WIDTH / 2, Constants.APP_HEIGHT / 2);
		cam.update();
		sb = new SpriteBatch();
		skin = new Skin();
		buttons = new String[] { "Play", "Settings", "Quit" };
		buttonArray = new ArrayList<TextButton>();

		Pixmap pixmap = new Pixmap(100, 100, Format.RGBA8888);
		pixmap.setColor(Color.CLEAR);
		pixmap.fill();
		skin.add("clear", new Texture(pixmap));

		pixmap.setColor(Color.BLUE);
		pixmap.fill();

		skin.add("blue", new Texture(pixmap));
		BitmapFont font = new BitmapFont();
		font.scale(0.8f);
		skin.add("default", font);

		TextButtonStyle tBSyle = new TextButtonStyle();
		tBSyle.up = skin.newDrawable("clear", Color.DARK_GRAY);
		tBSyle.down = skin.newDrawable("blue", Color.DARK_GRAY);
		tBSyle.checked = skin.newDrawable("blue", Color.BLUE);
		tBSyle.over = skin.newDrawable("blue", Color.BLUE);

		tBSyle.font = skin.getFont("default");
		skin.add("default", tBSyle);
		TextButton prev = null;
		float b = 0;
		for (int i = 0; i < buttons.length; i++) {
			TextButton tmp = new TextButton(buttons[i], tBSyle);
			if (prev != null)
				b += prev.getHeight();
			tmp.setPosition(Constants.APP_WIDTH / 2 - tmp.getWidth() / 2,
					Constants.APP_HEIGHT / 2 - b);
			stage.addActor(tmp);
			buttonArray.add(tmp);
			prev = tmp;
		}

		/*
		 * textButton = new TextButton("PLAY",tBSyle);
		 * textButton.setPosition(Constants.APP_WIDTH/2-textButton.getWidth()/2,
		 * Constants.APP_HEIGHT/2);
		 * 
		 * textClose = new TextButton("QUIT",tBSyle);
		 * textClose.setPosition(Constants.APP_WIDTH/2-textClose.getWidth()/2,
		 * Constants.APP_HEIGHT/2-textButton.getHeight());
		 * stage.addActor(textClose);
		 */

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		for (TextButton button : buttonArray) {
			final String tex = button.getText().toString();
			button.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					// TODO Auto-generated method stub
					if (tex.equals("Play")) {
						System.out.println("Open game!");
						game.setScreen(game.gameScreen);
					}
					if (tex.equals("Quit")) {
						System.out.println("Close game!");
						Gdx.app.exit();
					}
					if(tex.equals("Settings")){
						System.out.println("TODO Settings menu!");
					}
				}
			});
		}

		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
		dispose();

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		stage.dispose();
		skin.dispose();

	}

}
