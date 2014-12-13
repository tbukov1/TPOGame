package utils;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MyAnimation {
	
	TextureRegion[] frame;
	float time, delay;
	int currentFrame;
	
	public MyAnimation(){}
	
	public MyAnimation(TextureRegion[] res){
		this(res, 1/12f);		
	}
	public MyAnimation(TextureRegion[] res, float delay){
		setFrame(res, delay);
	}

	public void setFrame(TextureRegion[] res, float delay2) {
		// TODO Auto-generated method stub
		frame = res;
		delay = delay2;
		time = 0;
		currentFrame = 0;
	}
	
	public void update(float df){
		if(delay <= 0) return;
		time += df;
		while(time >= delay)
			step();
	}

	private void step() {
		// TODO Auto-generated method stub
		time -= delay;
		currentFrame++;
		if(currentFrame == frame.length){
			currentFrame = 0;
		}
	}
	
	public TextureRegion getFrame(){
		return frame[currentFrame];
	}
}
