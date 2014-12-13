package utils;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;

public class WorldUtils {
	
	
	public static World createWorld() {
		return new World(Constants.WORLD_G,true);
	}
	
	public static Body createGround(World world,String file){
		//TODO nekak dodat use elemente mape u ta body
		BodyDef bd = new BodyDef();
		bd.position.set(Constants.GROUND_POS);
		bd.type = BodyType.StaticBody;
		
		Body body = world.createBody(bd);
		
		PolygonShape pgo = new PolygonShape();// tole nekak zamenat al kwa nevem
		pgo.setAsBox(25/2,2/2);
		body.createFixture(pgo, Constants.GROUND_DENSITY);
		pgo.dispose();
		return body;
	}
	
}
