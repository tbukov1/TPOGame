package screens;

import tpo.game.TPOGame2;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class SpriteScreen implements Screen{
	Stage stage;
	Texture tex;
	TPOGame2 game;
	SpriteBatch sb = new SpriteBatch();
	Image s;
	
	public SpriteScreen(TPOGame2 game,String path){
		this.game = game;
		tex = new Texture(Gdx.files.internal(path));
		s = new Image(tex);
		float scaleX = 1, scaleY = 1;
		if(Gdx.graphics.getWidth()!= 800)
			scaleX = 2.4f;
		if(Gdx.graphics.getHeight()!= 400)
			scaleY = 2.7f;
		
		s.setScaleX(scaleX);
		s.setScaleY(scaleY);
		stage = new Stage();
		
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		stage.addActor(s);
		s.addAction(Actions.sequence(Actions.alpha(0)
                ,Actions.fadeIn(0.5f),Actions.delay(30),Actions.run(new Runnable() {
     @Override
     public void run() {
         ((Game)Gdx.app.getApplicationListener()).setScreen(new SpriteScreen(game, "data/krajfar_splash.png"));
     }
 })));

	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
//		sb.begin();
//		s.draw(sb);
//		sb.end();
		processInput();
		stage.act();
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
		tex.dispose();
	}
	
	private void processInput(){
		if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
			game.setScreen(game.mainMenuScreen);
		}
		if(Gdx.input.isTouched()){
			game.setScreen(game.mainMenuScreen);
		}
			
	}

}
