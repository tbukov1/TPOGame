package stages;

import utils.Camera;
import utils.MapBodyManager;
import utils.WorldUtils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.Stage;

import elements.Player;
import elements.MyMap;

public class GameStages extends Stage implements InputProcessor {
	private World world;
	private Body ground;
	private SpriteBatch sb;
	private BodyDef groundDef;
	private MyMap map;

	float timeStep = 1 / 60f;
	float accumulator = 0f;

	OrthographicCamera camera;
	Box2DDebugRenderer renderer;
	MapBodyManager mbm;

	Player player;

	public GameStages() {
		setupCamera();
		sb = new SpriteBatch();
		mbm = new MapBodyManager(world, 10, new FileHandle(
				"data/map/materials.json"), 0);
		world = WorldUtils.createWorld();
		map = WorldUtils.createGround(world, "data/map/testMapa.tmx");
		ground = mbm.createPhysics(map.tiledMap, "okvir");
		// world.createBody(groundDef);
		renderer = new Box2DDebugRenderer();
		Gdx.input.setInputProcessor(this);
		createPlayer("data/player/sprites_player_3.png");
	}

	private void setupCamera() {
		// TODO Auto-generated method stub
		OrthographicCamera tmp = new OrthographicCamera(600,300);
//		tmp.position.set(tmp.viewportWidth / 2,
//				tmp.viewportHeight / 2, 0f);
//		tmp.update();
		Camera proba = new Camera(tmp);
		camera = Camera.getCamera();
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
	}

	private void createPlayer(String tex) {
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();

		bdef.position.set(0,0);
		bdef.type = BodyType.DynamicBody;
		Body body = world.createBody(bdef);

		shape.setAsBox(50,	50);
		fdef.shape = shape;
		body.createFixture(fdef).setUserData("player");

		player = new Player(body, tex);
		body.setUserData(player);
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		super.draw();
		int dir = processInput();
		if(dir == -1)
			player.stop();
		map.moveCamera(dir);
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
