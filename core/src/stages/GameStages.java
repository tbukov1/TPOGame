package stages;

import java.util.ArrayList;

import tpo.game.TPOGame2;
import utils.Camera;
import utils.Constants;
import utils.GameStates;
import utils.MapBodyManager;
import utils.WorldUtils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

import elements.MyMap;
import elements.Player;

public class GameStages extends Stage implements InputProcessor {
	private World world;
	private ArrayList<Body> ground;
	private SpriteBatch sb;
	private BodyDef groundDef;
	private MyMap map;
	TPOGame2 game;	
	
	float startX = 600;
	float startY = 500;

	float timeStep = 1 / 60f;
	float accumulator = 0f;

	OrthographicCamera camera;
	Box2DDebugRenderer renderer;
	MapBodyManager mbm;

	Player player;

	public GameStages(TPOGame2 game) {
		setupCamera(startX,startY);
		sb = new SpriteBatch();
		world = WorldUtils.createWorld();
		mbm = new MapBodyManager(world, 1, new FileHandle(
				"data/map/materials.json"), 0);
		
		map = WorldUtils.createGround(world, Constants.MAP_NAMES[Constants.DESERT]);
		ground = mbm.createPhysics(map.tiledMap, "physics");
		// world.createBody(groundDef);
		
		renderer = new Box2DDebugRenderer();
		Gdx.input.setInputProcessor(this);
		createPlayer("data/player/sprites_player_3.png",startX,startY);
		this.game = game;
	}

	private void setupCamera(float x, float y) {
		// TODO Auto-generated method stub
		//OrthographicCamera tmp = new OrthographicCamera(300,150);
		
		
		camera = Camera.getCamera();
		camera.position.set(x,
				y, 0f);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		accumulator += delta;

		while (accumulator >= delta) {
			world.step(timeStep, 6, 2);
			accumulator -= timeStep;
		}
		player.update(delta);
		float x, y;
		x = Math.max(player.getPosition().x, 400);
		x = Math.min(x, 624);
		y = Math.max(player.getPosition().y, 200);
		y = Math.min(y, 824);
		
		
		camera.position.set(x, y, 0f);
		
		System.out.println("x: "+player.getPosition().x + " y:" + player.getPosition().y);
	}

	private void createPlayer(String tex, float x, float y) {
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();

		bdef.position.set(x,y);
		bdef.type = BodyType.DynamicBody;
		//bdef.linearVelocity.set(0,0);
		Body body = world.createBody(bdef);

		shape.setAsBox(10,	10);
		fdef.shape = shape;
		body.createFixture(fdef).setUserData("player");

		player = new Player(body, tex, 0.17f, 0.17f);
		body.setUserData(player);
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		super.draw();
		int dir = processInput();
		if(dir == -1)
			player.stop();
		//map.moveCamera(dir);
		Camera.getCamera().update();
		
		
		map.render();
		player.render(sb);

		renderer.render(world, camera.combined);
	}

	private int processInput() {
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			player.move(1);
			return 1;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			player.move(3);
			return 3;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			player.move(2);
			return 2;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			player.move(0);
			return 0;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.Q))
			game.state = GameStates.QUESTION;
		return -1;
	}

	/*
	 * @Override public boolean keyDown(int keycode) { if (keycode ==
	 * Input.Keys.LEFT) player.move(1); if (keycode == Input.Keys.RIGHT)
	 * player.move(3); if (keycode == Input.Keys.UP) player.move(2); if (keycode
	 * == Input.Keys.DOWN) player.move(0); return false; }
	 */

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Input.Keys.LEFT)
			player.stop();
		if (keycode == Input.Keys.RIGHT)
			player.stop();
		if (keycode == Input.Keys.UP)
			player.stop();
		if (keycode == Input.Keys.DOWN)
			player.stop();
		return false;

	}
}
