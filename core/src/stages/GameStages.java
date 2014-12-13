package stages;

import utils.MapBodyManager;
import utils.WorldUtils;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

import elements.MyMap;

public class GameStages extends Stage{
	private World world;
	private Body ground;
	private BodyDef groundDef;
	private MyMap map;
	
	float timeStep = 1/60f;
	float accumulator = 0f;
	
	OrthographicCamera camera;
	Box2DDebugRenderer renderer;
	MapBodyManager mbm;
	
	public GameStages(){
		mbm = new MapBodyManager(world, 10, new FileHandle("data/map/materials.json"), 0);
		world = WorldUtils.createWorld();
		map = WorldUtils.createGround(world, "data/map/testMapa.tmx");
		ground = mbm.createPhysics(map.tiledMap, "okvir");
		//world.createBody(groundDef);
		renderer = new Box2DDebugRenderer();
		
		setupCamera();
	}

	private void setupCamera() {
		// TODO Auto-generated method stub
		camera = new OrthographicCamera(20, 13);
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
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		super.draw();
		
		map.render();
		
		renderer.render(world, camera.combined);
	}
}
