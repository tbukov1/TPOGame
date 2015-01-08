package tpo.game;

import screens.GameScreen;
import screens.MainMenu;
import screens.SpriteScreen;
import utils.Constants;
import utils.GameStates;

import com.badlogic.gdx.Game;

public class TPOGame2 extends Game {
	public MainMenu mainMenuScreen;
	public GameScreen gameScreen;
	SpriteScreen spriteScreen;
	public int state, stage;
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		state = GameStates.GAME;
		stage = Constants.DESERT;
		mainMenuScreen = new MainMenu(this);
		gameScreen = new GameScreen(this);
		spriteScreen = new SpriteScreen(this, "data/krajfar_splash.png");

//		setScreen(mainMenuScreen);
//		setScreen(gameScreen);
		setScreen(spriteScreen);
	}

}
