package utils;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.World;

import elements.MyMap;

public class WorldUtils {
	
	public static World createWorld() {
		return new World(Constants.WORLD_G,true);
	}
	
	public static MyMap createGround(World world,String file){
		//TODO nekak dodat use elemente mape u ta body
		MyMap map = new MyMap(file);
	


	/*	BodyDef bd = new BodyDef();
		bd.position.set(Constants.GROUND_POS);
		bd.type = BodyType.StaticBody;
<<<<<<< HEAD
		
		Body body = world.createBody(bd);
		
		PolygonShape pgo = new PolygonShape();// tole nekak zamenat al kwa nevem
		pgo.setAsBox(50/2,50/2);
=======
		*/

	/*	PolygonShape pgo = new PolygonShape();// tole nekak zamenat al kwa nevem
		pgo.setAsBox(25/2,2/2);
>>>>>>> 64c05a62b0c28747ba15ad97b64c5548fae6b398
		body.createFixture(pgo, Constants.GROUND_DENSITY);
		pgo.dispose();*/
		return map;
	}
	
}
