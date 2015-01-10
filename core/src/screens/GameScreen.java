package screens;

import java.util.ArrayList;
import java.util.Random;

import question_parser.Question;
import stages.GameStages;
import stages.QuestionStage;
import tpo.game.TPOGame2;
import utils.Constants;
import utils.GameStates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameScreen implements Screen{
	
	ArrayList<Stage> stages;
	GameStages stage;
	QuestionStage qStage;
	TPOGame2 game;	
	boolean qStageFirst;
	boolean gameStageFirst;
	Random naklj;
	
	public GameScreen(TPOGame2 game){
		this.game = game;
		naklj = new Random();
		stages = new ArrayList<Stage>();
		stages.add(new GameStages(game, Constants.DESERT, Constants.SUBJECT_QUESTION[0]));
		stages.add(new GameStages(game, Constants.FOREST, Constants.SUBJECT_QUESTION[1]));
		stages.add(new GameStages(game, Constants.SNOW, Constants.SUBJECT_QUESTION[2]));
		stages.add(new GameStages(game, Constants.CAVE, Constants.SUBJECT_QUESTION[3]));
		qStage = new QuestionStage(game);
		stages.add(qStage);
		qStageFirst = true;
		gameStageFirst = true;
		
		setStage(game.stage);
	}
	
	public void setStage(int i){
		if(i <= 3){
			stage = (GameStages)stages.get(i);
			
		}
		else{
			qStage = (QuestionStage)stages.get(i);
		}
		game.stage = i;
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if(game.state == GameStates.GAME){
			if(gameStageFirst){
				qStage.unfocusAll();
				gameStageFirst = false;
				Gdx.input.setInputProcessor(stage);
				qStageFirst = true;
			}
			stage.draw();
			stage.act(delta);
		}
		else if(game.state == GameStates.QUESTION){
			if(qStageFirst){		
				//TODO izbere tist uprašane, ke ga ma monster ke se ga dotakneš, èe še ni rešen
				//se ne bo rabil ke bo narjen collison 
				//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
				Question tmp = null;
				int index = 0;
				do{
					if(index > game.questions.get(Constants.SUBJECT_QUESTION[game.stage]).size()){
						tmp = null;
						break;
					}
					tmp= game.questions.get(Constants.SUBJECT_QUESTION[game.stage]).get(naklj.nextInt(game.questions.get(Constants.SUBJECT_QUESTION[game.stage]).size()));
					index++;
				}while(tmp.answered);
				//------------------------------------------------------------------------------------
				qStage = new QuestionStage(game);
				qStage.setQuestion(tmp);
				Gdx.input.setInputProcessor(qStage);
				qStage.makeText();
				qStage.setColor(232, 32, 23);
				qStageFirst = false;
				gameStageFirst = true;
				}
			qStage.draw();
			qStage.act(delta);
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
}
