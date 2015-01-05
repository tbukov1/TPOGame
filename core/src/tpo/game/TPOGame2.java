package tpo.game;

import screens.GameScreen;
import screens.MainMenu;
import screens.SpriteScreen;

import com.badlogic.gdx.Game;

public class TPOGame2 extends Game {
	public MainMenu mainMenuScreen;
	public GameScreen gameScreen;
	SpriteScreen spriteScreen;
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		mainMenuScreen = new MainMenu(this);
		gameScreen = new GameScreen(this);
		spriteScreen = new SpriteScreen(this, "data/krajfar_splash.png");

//		setScreen(mainMenuScreen);
//		setScreen(gameScreen);
		setScreen(spriteScreen);
	}

}
