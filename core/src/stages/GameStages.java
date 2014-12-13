package stages;

import utils.WorldUtils;

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

public class GameStages extends Stage{
	private World world;
	private Body ground;
	private SpriteBatch sb;
	
	float timeStep = 1/60f;
	float accumulator = 0f;
	
	OrthographicCamera camera;
	Box2DDebugRenderer renderer;
	
	Player player;
	
	public GameStages(){
		sb = new SpriteBatch();
		world = WorldUtils.createWorld();
		ground = WorldUtils.createGround(world, "data/map/testMapa.tmx");
		renderer = new Box2DDebugRenderer();
		createPlayer("data/player/sprites_player_3.png");
		setupCamera();
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
		
		bdef.position.set(300,300);
		bdef.type = BodyType.DynamicBody;
//		bdef.linearVelocity.set(1,0);
		Body body = world.createBody(bdef);
		
		shape.setAsBox(13 , 13 );
		fdef.shape = shape;
		body.createFixture(fdef).setUserData("player");
		
		player = new Player(body,tex);
		body.setUserData(player);
	}
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		super.draw();
		player.render(sb);
		renderer.render(world, camera.combined);
	}
}
