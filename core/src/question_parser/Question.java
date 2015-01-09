package question_parser;


public class Question {
	String id;
	String text;
	// String answer1,answer2,answer3,answer4;
	String[] answers;
	// String correctAnswer;
	int correctAnswer;

	public boolean answered;
	public int attempt;
	
	public Question(){}
	
	
	public Question(String id, String text, String[] answers, int correctAnswer) {
		this.id = id;
		this.text = text;
		this.answers = answers;
		this.correctAnswer = correctAnswer;
		answered = false;
		attempt = 1;
	}

	public String getText() {
		return text;
	}

	// public ArrayList<String> returnAnswers(){
	// ArrayList<String> listic = new ArrayList<String>();
	// listic.add(answer1);
	// listic.add(answer2);
	// listic.add(answer3);
	// listic.add(answer4);
	//
	// return listic;
	// }
	public String[] getAnswers() {
		return answers;
	}

	public int getcorrectAnswer() {
		return correctAnswer;
	}

}
