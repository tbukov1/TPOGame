package question_parser;


import java.util.ArrayList;

public class Question {
	String id;
	String text;
	ArrayList<String> answers;
	int correctAnswer;

	public boolean answered;
	public int attempt;
	
	public Question(){}
	
	
	public Question(String id, String text, ArrayList<String> answers, int correctAnswer) {
		this.id = id;
		this.text = text;
		this.answers = answers;
		this.correctAnswer = correctAnswer;
		answered = false;
		attempt = 1;
	}

	public String returnText() {
		return text;
	}

	public ArrayList<String> returnAnswers() {
		return answers;
	}

	public int returncorrectAnswer() {
		return correctAnswer;
	}

}
