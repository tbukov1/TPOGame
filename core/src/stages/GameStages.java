package stages;

import java.util.ArrayList;
import java.util.Random;

import tpo.game.TPOGame2;
import utils.Camera;
import utils.Constants;
import utils.CustomContactListener;
import utils.GameStates;
import utils.MapBodyManager;
import utils.WorldUtils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

import elements.Monster;
import elements.MyMap;
import elements.Player;
import elements.Points;

public class GameStages extends Stage implements InputProcessor {
	private World world;
	private ArrayList<Body> ground;
	private ArrayList<Monster> monsters;
	private Points hudPoints;
 	private SpriteBatch sb;
	private BodyDef groundDef;
	private MyMap map;
	private CustomContactListener listener;
	TPOGame2 game;	
	
	float startX = 600; 
	float startY = 500;

	float timeStep = 1 / 60f;
	float accumulator = 0f;
	int currentStage;

	OrthographicCamera camera;
	Box2DDebugRenderer renderer;
	MapBodyManager mbm;
	String subject;
	private boolean canLeave;

	Player player;

	public GameStages(TPOGame2 game, int curStage, String subject) {
		setupCamera(startX,startY);
		this.subject = subject;
		sb = new SpriteBatch();
		world = WorldUtils.createWorld();
		listener = new CustomContactListener(game, this);
		world.setContactListener(listener);
		mbm = new MapBodyManager(world, 1, new FileHandle(
				"data/map/materials.json"), 0);
		currentStage = curStage;
		map = WorldUtils.createGround(world, Constants.MAP_NAMES[currentStage]);
		ground = mbm.createPhysics(map.tiledMap, "physics");
		// world.createBody(groundDef);

		this.game = game;
		renderer = new Box2DDebugRenderer();
		Gdx.input.setInputProcessor(this);
		
		createPlayer("data/player/sprites_player_3.png",startX,startY);
		
		hudPoints = new Points(game.points);
		
		createMonsters();
		
		canLeave = false;
	}
	
	public ArrayList<Monster> getMonsters(){
		return monsters;
	}
	
	public World getWorld(){
		return world;
	}

	public void createMonsters(){
		//create monsters
		monsters = new ArrayList<Monster>();
		Random naklj = new Random();		
		for (int i = 0; i < game.questions.get(subject).size(); i++) {
			do{
			int x = naklj.nextInt(900)+50;
			int y = naklj.nextInt(900)+50;
			if(!((x > Constants.PORT_LOCATIONS[currentStage].x -15) &&
					(x < Constants.PORT_LOCATIONS[currentStage].x +15))){
				
				if(!((y > Constants.PORT_LOCATIONS[currentStage].y-10) &&
						(y < Constants.PORT_LOCATIONS[currentStage].y+10))){
					createMonster(Constants.MONSTERS[naklj.nextInt(Constants.MONSTERS.length)], x, y,i);
					break;
				}}
			}while(true);
		}
	}
	
	
	
	private void setupCamera(float x, float y) {
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
		
		/*for(Body b : listener.getBodiesToRemove()){
			world.destroyBody(b);
		}*/
		player.update(delta);
		float x, y;
		x = Math.max(player.getPosition().x, 400);
		x = Math.min(x, 624);
		y = Math.max(player.getPosition().y, 200);
		y = Math.min(y, 824);
		
		canLeave = true;
		for(Monster m : monsters){
			if (m.question.answered == false) {
				canLeave = false;
			}
		}
		camera.position.set(x, y, 0f);
		//pogledam èe je na portu
		if((player.getPosition().x > Constants.PORT_LOCATIONS[currentStage].x -15) &&
				(player.getPosition().x < Constants.PORT_LOCATIONS[currentStage].x +15)){
			
			if((player.getPosition().y > Constants.PORT_LOCATIONS[currentStage].y-10) &&
					(player.getPosition().y < Constants.PORT_LOCATIONS[currentStage].y+10)){
				switchStage();
				return;
			}
			
		}
		
//		System.out.println("x: "+player.getPosition().x + " y:" + player.getPosition().y);
	}
	
	
	public void switchStage(){
		if (canLeave){
			canLeave = false;
			if(currentStage == Constants.CAVE){
				game.gameScreen.setStage(Constants.DESERT);
			}else {
				game.gameScreen.setStage(currentStage+1);
			}			
			//TODO piši v datoteko
			game.fh.writeString("points:"+game.points+"\n\rstage:"+game.stage,false);
		}
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
	private void createMonster(String tex, float x, float y, int count){
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();

		bdef.position.set(x,y);
		bdef.type = BodyType.DynamicBody;
		Body body = world.createBody(bdef);
		
		
		MassData md  = new MassData();
		md.mass = 5000000f;
		body.setMassData(md);

		shape.setAsBox(40,	40);
		fdef.shape = shape;
		body.createFixture(fdef).setUserData("monster"+count);
		Random naklj = new Random();	
		
		monsters.add(new Monster(body, tex, game.questions.get(subject).get(count), 0.30f, 0.30f));
		body.setUserData(monsters.get(count));
	}

	@Override
	public void draw() {
		super.draw();
		int dir = processInput();
		if(dir == -1)
			player.stop();
		//map.moveCamera(dir);
		Camera.getCamera().update();
		
		map.render();		
		player.render(sb);
		for (Monster monster : monsters) {
			if(!monster.question.answered)
				monster.render(sb);
		}

		renderer.render(world, camera.combined);
		
		
		hudPoints.render(game.points);

	}

	private int processInput() {
		Vector3 touchPos;
		
		touchPos = new Vector3();
		
		int deviceWidth = Gdx.graphics.getWidth();
   	    int deviceHeight = Gdx.graphics.getHeight();

		
		if (Gdx.input.isTouched()) {
	         touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0); //when the screen is touched, the coordinates are inserted into the vector
	         if(touchPos.x > 0 && touchPos.x < 100){
	        	 player.move(1);
	        	 return 1;
	         }
	         if(touchPos.x > deviceWidth - 100 && touchPos.x < deviceWidth){
	        	 player.move(3);
	        	 return 3;
	         }
	         if(touchPos.x > 100 && touchPos.x < deviceWidth - 100 && touchPos.y > 0 && touchPos.y < 100){
	        	 player.move(2);
	        	 return 2;
	         }
	         if(touchPos.x > 100 && touchPos.x < deviceWidth - 100 && touchPos.y > deviceHeight - 100 && touchPos.y < deviceHeight){
	        	 player.move(0);
	        	 return 0;
	         }
		}
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
