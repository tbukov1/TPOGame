package stages;

import utils.WorldUtils;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameStages extends Stage{
	private World world;
	private Body ground;
	
	float timeStep = 1/60f;
	float accumulator = 0f;
	
	OrthographicCamera camera;
	Box2DDebugRenderer renderer;
	
	public GameStages(){
		world = WorldUtils.createWorld();
		ground = WorldUtils.createGround(world, "data/map/testMapa.tmx");
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
		renderer.render(world, camera.combined);
	}
}
