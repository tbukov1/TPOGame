package tpo.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import tpo.game.TPOGame;
import utils.Constants;
//import tpo.game.Touch;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Constants.APP_WIDHT;
		config.width = Constants.APP_HEIGHT;
		new LwjglApplication(new TPOGame(), config);
	}
}
