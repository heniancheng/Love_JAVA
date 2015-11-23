import java.io.File;
import java.io.IOException;

import javacore.io.PublicConstants;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;


public class XMLTest {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new File(PublicConstants.FILE_SRC_PATH +"myXML.xml"));
		Element root = doc.getDocumentElement();
		NodeList childNode = root.getChildNodes();
		for(int i = 0; i < childNode.getLength(); i++){
			Node child = childNode.item(i);
			if(child instanceof Element){
				Element childElement = (Element) child;
				Text textNode = (Text) childElement.getFirstChild();
				String text = textNode.getData().trim();
				if(childElement.getTagName().equals("name")){
					System.out.print("name : " + text);
					String atrr = childElement.getAttribute("atrr");
					System.out.println("; atrr : " + atrr);
				}
				else if(childElement.getTagName().equals("size")){
					System.out.print("size : " + Integer.parseInt(text));
					String atrr = childElement.getAttribute("atrr");
					System.out.println("; atrr : " + atrr);
				}
			}
		}
	}

} 
