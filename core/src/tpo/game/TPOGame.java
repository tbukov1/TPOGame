package tpo.game;

import screens.GameScreen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

import elements.Map;
import elements.Player;

public class TPOGame extends Game {

	SpriteBatch spriteBatch;
	Player player;
	Map map; 
	public float width ,height;
	World world;
	
	@Override
	public void create() {
		/*width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		
		spriteBatch = new SpriteBatch();

		map = new Map("data/map/testMapa.tmx");
		world = new World(new Vector2(0, 0), true);
		player = new Player("data/player/sprites_player_3.png", this);*/
		setScreen(new GameScreen());
	}

}
