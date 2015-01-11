package utils;

import java.util.ArrayList;

import question_parser.Question;
import stages.GameStages;
import tpo.game.TPOGame2;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import elements.Monster;

public class CustomContactListener implements ContactListener{
	
	TPOGame2 game;
	GameStages stage;
	ArrayList<Body> toRemove;
	
	public CustomContactListener(TPOGame2 game, GameStages stage){
		this.game = game;
		this.stage = stage;
		toRemove = new ArrayList<Body>();
	}
	 
	@Override
	public void beginContact(Contact contact) {
     	Fixture fixtureA = contact.getFixtureA();
         Fixture fixtureB = contact.getFixtureB();
         int newState;
         if (fixtureA.getUserData() != null && fixtureB.getUserData() != null){
             if(fixtureA.getUserData().equals("player") || fixtureB.getUserData().equals("player") ){
             	newState = GameStates.QUESTION;
             	int index;
             	String userData="";
             	if(((String)fixtureA.getUserData()).startsWith("monster")){
             		userData = (String) fixtureA.getUserData();
             	}
             	else{
             		if(((String)fixtureB.getUserData()).startsWith("monster")){
             			userData = (String) fixtureB.getUserData();
             		}else{
             			newState = GameStates.GAME;
             		}
               	}
             	
             if(!userData.equals("")){		
                index = Integer.parseInt(userData.substring(userData.length()-1));
         		game.gameScreen.fromMonster = stage.getMonsters().get(index).question;
         		if(game.gameScreen.fromMonster.answered == true){
         			game.gameScreen.fromMonster = null;
         			newState = GameStates.GAME;
         		}
             }
             
             game.state = newState;
             		
             		
             		
             		//System.out.println("contact");
             	
             }
         }  
         //Gdx.app.log("beginContact", "between " + fixtureA.toString() + " and " + fixtureB.toString());
     }

     @Override
     public void endContact(Contact contact) {
    /*	 Fixture fixtureA = contact.getFixtureA();
         Fixture fixtureB = contact.getFixtureB();
         
         
         if (fixtureA.getUserData() != null && fixtureB.getUserData() != null){
             if(fixtureA.getUserData().equals("player") || fixtureB.getUserData().equals("player") ){
             	String userData = "";
             	
             	int index;
             	if(((String)fixtureA.getUserData()).startsWith("monster")){
             		userData = (String) fixtureA.getUserData();
             		
             	}
             	
             	if(((String)fixtureB.getUserData()).startsWith("monster")){
             		userData = (String) fixtureB.getUserData();
                 		
             		
               	}
             	
             		
             		
             	if(!userData.equals("")){	
             		index = Integer.parseInt(userData.substring(userData.length()-1));
             		Monster mon = stage.getMonsters().get(index);
             		if(mon.question.answered == true){
             			toRemove.add(mon.body);
             		}	
         		
             	}	
             		//System.out.println("contact");
             	
             }
         } */ 
     }

     @Override
     public void preSolve(Contact contact, Manifold oldManifold) {
    	 Fixture fixtureA = contact.getFixtureA();
         Fixture fixtureB = contact.getFixtureB();
         
         
         if (fixtureA.getUserData() != null && fixtureB.getUserData() != null){
             if(fixtureA.getUserData().equals("player") || fixtureB.getUserData().equals("player") ){
             	String userData = "";
             	
             	int index;
             	if(((String)fixtureA.getUserData()).startsWith("monster")){
             		userData = (String) fixtureA.getUserData();
             		
             	}
             	
             	if(((String)fixtureB.getUserData()).startsWith("monster")){
             		userData = (String) fixtureB.getUserData();
                 		
             		
               	}
             	
             		
             		
             	if(!userData.equals("")){	
             		index = Integer.parseInt(userData.substring(userData.length()-1));
             		Monster mon = stage.getMonsters().get(index);
             		if(mon.question.answered == true){
             			contact.setEnabled(false);
             		}	
         		
             	}	
             		//System.out.println("contact");
             	
             }
         }  
     }

     @Override
     public void postSolve(Contact contact, ContactImpulse impulse) {
     }
     
     public ArrayList<Body> getBodiesToRemove(){
    	 return toRemove;
     }

 

}
