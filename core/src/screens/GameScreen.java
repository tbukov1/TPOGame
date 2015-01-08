package screens;

import java.util.ArrayList;

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
	
	public GameScreen(TPOGame2 game){
		this.game = game;
		stages = new ArrayList<Stage>();
		stages.add(new GameStages(game, Constants.DESERT));
		stages.add(new GameStages(game, Constants.FOREST));
		stages.add(new GameStages(game, Constants.SNOW));
		stages.add(new GameStages(game, Constants.CAVE));
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
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//nekam shranmo ker state je in pol glede tega stata rišemo al špilo al pa question

		if(game.state == GameStates.GAME){
			if(gameStageFirst){
				gameStageFirst = false;
				Gdx.input.setInputProcessor(stage);
				qStageFirst = true;
			}
			stage.draw();
			stage.act(delta);
		}
		else if(game.state == GameStates.QUESTION){
			qStage.setAll("A to sploh kej dela?",new String[]{"Da","Ne","Nevem","Mogoèe"},1);
			if(qStageFirst){
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
