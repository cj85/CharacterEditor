import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
 
public class HeadParser {
 
	public static ArrayList<Head> parse() {
 
	  try {
        ArrayList<Head> toReturn=new ArrayList<Head>();
		File fXmlFile = new File("./Heads.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
 
//		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		NodeList nList = doc.getElementsByTagName("Head");
		for (int temp = 0; temp < nList.getLength(); temp++) {
 
		   Node nNode = nList.item(temp);
		   if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			   Head head=new Head();
		      Element eElement = (Element) nNode;
//		      System.out.println(" Name : " + getTagValue("name", eElement));
		      head.setName(getTagValue("name", eElement));
//		      System.out.println(" Property : " + getTagValue("property", eElement));
		      head.setProperty(getTagValue("property", eElement));
              toReturn.add(head);
		   }
		}
		return toReturn;
	  } catch (Exception e) {
		e.printStackTrace();
	  }
	return null;
  }
 
  private static String getTagValue(String sTag, Element eElement) {
	NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
 
        Node nValue = (Node) nlList.item(0);
 
	return nValue.getNodeValue();
  }
 
}