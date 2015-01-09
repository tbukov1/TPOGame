package tpo.game;

import java.nio.file.Files;
import java.util.ArrayList;

import question_parser.Question;

import screens.GameScreen;
import screens.MainMenu;
import screens.SpriteScreen;
import utils.Constants;
import utils.GameStates;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class TPOGame2 extends Game {
	public MainMenu mainMenuScreen;
	public GameScreen gameScreen;
	SpriteScreen spriteScreen;
	public int state, stage;
	public float points;
	public FileHandle fh;	

	public ArrayList<Question> questions;
	
	@Override
	public void create() {
		questions = new ArrayList<Question>();
		try{
			fh = Gdx.files.internal("data/file.txt");
			String[] file = fh.readString().split("\n");
			points = Float.parseFloat(file[0].replaceAll("\\r|\\n", "").split(":")[1]);
			stage = Integer.parseInt(file[1].replaceAll("\\r|\\n", "").split(":")[1]);
		}
		catch(Exception e){
			stage = Constants.DESERT;
		}
		

		//TODO s parserjem nafili questions glede na stage;
		questions.add(new Question("bla","A to sploh kej dela0?",new String[]{"Da","Ne","Nevem","Mogo�e"},1));
		questions.add(new Question("muhehuha","A to sploh kej dela1?",new String[]{"Da","Ne","Nevem","Mogo�e"},0));
		questions.add(new Question("muhehuha","A to sploh kej dela2?",new String[]{"Da","Ne","Nevem","Mogo�e"},0));
		questions.add(new Question("muhehuha","A to sploh kej dela3?",new String[]{"Da","Ne","Nevem","Mogo�e"},0));
		questions.add(new Question("muhehuha","A to sploh kej dela4?",new String[]{"Da","Ne","Nevem","Mogo�e"},0));
		questions.add(new Question("muhehuha","A to sploh kej dela5?",new String[]{"Da","Ne","Nevem","Mogo�e"},0));
		questions.add(new Question("muhehuha","A to sploh kej dela6?",new String[]{"Da","Ne","Nevem","Mogo�e"},0));
		questions.add(new Question("muhehuha","A to sploh kej dela7?",new String[]{"Da","Ne","Nevem","Mogo�e"},0));
		
		state = GameStates.GAME;
//		stage = Constants.DESERT;//glede na to kje je ustou u prej�no, 
		mainMenuScreen = new MainMenu(this);
		gameScreen = new GameScreen(this);
		spriteScreen = new SpriteScreen(this, "data/krajfar_splash.png");
		//preber v vrednosti gamePoints pa stage iz datoteke

//		setScreen(mainMenuScreen);
//		setScreen(gameScreen);
		setScreen(spriteScreen);
	}

}
