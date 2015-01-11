package question_parser;





import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class Parser {
    	 
	public static ArrayList<Question> getQuestion(String filepath, String category){
		

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			FileHandle fh = Gdx.files.internal(filepath);
			Document document = builder.parse(fh.read());

			NodeList nodeList = document.getDocumentElement().getChildNodes();
		
			Node tempnode = document;
			
			for(int i = 0; i < nodeList.getLength(); i++){
				Node node = nodeList.item(i);
				if(node instanceof Element){
					if(node.getAttributes().getNamedItem("type").getNodeValue().equals(category)){
						tempnode = node;
						break;
						
					}					
				}								
			}
			
			nodeList = tempnode.getChildNodes();
			NodeList insides;
			NodeList nodeListAnswers;
			ArrayList<Question> questions = new ArrayList<Question>();
			Question que = new Question();
			
			for(int i = 0; i < nodeList.getLength(); i++){
				Node node = nodeList.item(i);
				insides = node.getChildNodes();
				if(node instanceof Element){
					for(int k = 0; k < insides.getLength(); k++){
						tempnode = insides.item(k);
						if(tempnode instanceof Element){	

							if(tempnode.getAttributes().getNamedItem("type").getNodeValue().equals("question")){
								que = new Question();
								que.text = tempnode.getFirstChild().getNodeValue();							
							} else {
								nodeListAnswers = tempnode.getChildNodes();
								ArrayList<String> answers = new ArrayList<String>();

								int trenutenAnswer = 0;
								for(int j = 0; j < nodeListAnswers.getLength(); j++){
									Node nodeAnswer = nodeListAnswers.item(j);
									if(nodeAnswer instanceof Element){
										
										answers.add(nodeAnswer.getTextContent()); 
										if(nodeAnswer.getAttributes().getLength() != 0){
											que.correctAnswer = trenutenAnswer;
										}
										trenutenAnswer += 1;
									}
								}
								que.answers = answers;
								
								questions.add(que);
							}
						}
					}
				}
			}

			return questions;
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;

	}
	

}
