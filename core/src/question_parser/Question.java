package question_parser;

import java.util.ArrayList;

public class Question {
	String id;
	String text;
//	String answer1,answer2,answer3,answer4;
	String[] answers;
//	String correctAnswer;
	int correctAnswer;
	
	public String returnText(){
		return text;
	}

//	public ArrayList<String> returnAnswers(){
//		ArrayList<String> listic = new ArrayList<String>();
//		listic.add(answer1);
//		listic.add(answer2);
//		listic.add(answer3);
//		listic.add(answer4);
//		
//		return listic;
//	}
	public String[] returnAnswers(){
		return answers;
	}

	public int returncorrectAnswer(){
		return correctAnswer;
	}

	
}
