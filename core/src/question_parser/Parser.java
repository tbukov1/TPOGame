package question_parser;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import java.util.ArrayList;

public class Parser {
    	 
	public ArrayList<Question> getQuestion(String filepath, String category, int nacin){
		
		File fXmlFile = new File(filepath);

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			
			//Get root element
			Element root = doc.getDocumentElement();
			Node tempnode = root;

			NodeList nodeList = root.getChildNodes();
			
			for(int i = 0; i < nodeList.getLength(); i++){
				Node node = nodeList.item(i);
				if(node instanceof Element){
					if(node.getAttributes().getNamedItem("type").getNodeValue() == category){
						tempnode = node;
						break;
						
					}					
				}								
			}
			
			nodeList = tempnode.getChildNodes();
			NodeList nodeListAnswers;
			ArrayList<Question> questions = new ArrayList<Question>();
			
			for(int i = 0; i < nodeList.getLength(); i++){
				Node node = nodeList.item(i);
				if(node instanceof Element){
					Question que = new Question();
					que.id = node.getAttributes().getNamedItem("number").getNodeValue();
					que.text = node.getFirstChild().getNodeValue();
					
					nodeListAnswers = node.getChildNodes();
					que.answer1 = nodeListAnswers.item(0).getNodeValue();
					que.answer2 = nodeListAnswers.item(1).getNodeValue();
					que.answer3 = nodeListAnswers.item(2).getNodeValue();
					que.answer4 = nodeListAnswers.item(3).getNodeValue();
					
					for(int j = 0; j < nodeListAnswers.getLength(); j++){
						Node nodeAnswer = nodeList.item(i);						
						if(nodeAnswer instanceof Element){
							if(nodeAnswer.getAttributes() != null){
								que.correctAnswer = nodeAnswer.getNodeValue();								
							}
						}
					}
					
					questions.add(que);
					
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
