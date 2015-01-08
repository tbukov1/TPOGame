package utils;

import com.badlogic.gdx.math.Vector2;

public class Constants {
	public static final int APP_WIDTH = 800;
	public static final int APP_HEIGHT = 400;
	
	public static final Vector2 WORLD_G = new Vector2(0,0);
	public static final Vector2 GROUND_POS = new Vector2(0,0);
	public static final float GROUND_DENSITY = 0f;
	
	public static final int DESERT = 0;
	public static final int FOREST = 1;
	public static final int SNOW = 2;
	public static final int CAVE = 3;
	
	public static final String[] SUBJECT_QUESTION = {"english","math","chemistry","history"};
	
	public static final String[] MAP_NAMES = {"data/map/desert2.tmx", "data/map/forest2.tmx", "data/map/snow2.tmx", "data/map/cave2.tmx"};
	public static final Vector2[] PORT_LOCATIONS = {new Vector2(912,945), new Vector2(912, 896), new Vector2(896,912), new Vector2(847,878)};
}
