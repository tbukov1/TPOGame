package tpo.game;

import java.util.ArrayList;
import java.util.HashMap;

import question_parser.Parser;
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
	public int points;
	public FileHandle fh;	
	
	public HashMap<String, ArrayList<Question>> questions;
	
	@Override
	public void create() {
//		questions = new ArrayList<Question>();
		questions = new HashMap<String, ArrayList<Question>>();
		try{
			fh = Gdx.files.local("file.txt");
			String[] file = fh.readString().split("\n");
			points = Integer.parseInt(file[0].replaceAll("\\r|\\n", "").split(":")[1]);
			stage = Integer.parseInt(file[1].replaceAll("\\r|\\n", "").split(":")[1]);
		}
		catch(Exception e){
			stage = Constants.DESERT;
			System.out.print("There is no file!");
		}
		

		//TODO s parserjem nafili questions glede na stage;
		for (String subject : Constants.SUBJECT_QUESTION) {
			questions.put(subject, Parser.getQuestion("data/Questions.xml", subject));
		}
//		questions = Parser.getQuestion("data/Questions.xml", Constants.SUBJECT_QUESTION[stage]);
		
		state = GameStates.GAME;
		mainMenuScreen = new MainMenu(this);
		gameScreen = new GameScreen(this);
		spriteScreen = new SpriteScreen(this, "data/krajfar_splash.png");
		//preber v vrednosti gamePoints pa stage iz datoteke

//		setScreen(mainMenuScreen);
//		setScreen(gameScreen);
		setScreen(spriteScreen);
	}

}
