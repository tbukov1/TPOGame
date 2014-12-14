package tpo.game;

import screens.GameScreen;
import screens.MainMenu;

import com.badlogic.gdx.Game;

public class TPOGame2 extends Game {
	MainMenu mainMenuScreen;
	public GameScreen gameScreen;
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		mainMenuScreen = new MainMenu(this);
		gameScreen = new GameScreen(this);
		
		setScreen(mainMenuScreen);
	}

}
