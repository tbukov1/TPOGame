package stages;

import utils.MapBodyManager;
import utils.WorldUtils;

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

public class GameStages extends Stage{
	private World world;
	private Body ground;
	private SpriteBatch sb;
	private BodyDef groundDef;
	private MyMap map;
	
	float timeStep = 1/60f;
	float accumulator = 0f;
	
	OrthographicCamera camera;
	Box2DDebugRenderer renderer;
	MapBodyManager mbm;
	
	Player player;
	
	public GameStages(){
		sb = new SpriteBatch();
		mbm = new MapBodyManager(world, 10, new FileHandle("data/map/materials.json"), 0);
		world = WorldUtils.createWorld();
		map = WorldUtils.createGround(world, "data/map/testMapa.tmx");
		ground = mbm.createPhysics(map.tiledMap, "okvir");
//		world.createBody(groundDef);
		renderer = new Box2DDebugRenderer();
		setupCamera();
		createPlayer("data/player/sprites_player_3.png");
	}

	private void setupCamera() {
		// TODO Auto-generated method stub
		camera = new OrthographicCamera(10, 60);
		camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2,0f);
		camera.update();
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		accumulator += delta;
		
		while (accumulator >= delta) {
			world.step(timeStep, 6, 2);
			accumulator -= timeStep;
			
		}
	}
	
	private void createPlayer(String tex){
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		
		bdef.position.set(camera.viewportWidth/2,camera.viewportHeight/2);
		bdef.type = BodyType.DynamicBody;
		Body body = world.createBody(bdef);
		
		shape.setAsBox(10 , 13 );
		fdef.shape = shape;
		body.createFixture(fdef).setUserData("player");
		
		player = new Player(body,tex);
		body.setUserData(player);
	}
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		super.draw();
		
		map.render();
		player.render(sb);
		
		renderer.render(world, camera.combined);
	}
}
