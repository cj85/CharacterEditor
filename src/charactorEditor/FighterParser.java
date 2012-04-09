package charactorEditor;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;

public class FighterParser {

	public static ArrayList<Fighter> parse() {

		try {
			ArrayList<Fighter> toReturn = new ArrayList<Fighter>();
			File fXmlFile = new File("./Fighters.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();


			NodeList nList = doc.getElementsByTagName("Fighter");
			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Fighter fighter = new Fighter();
					Element eElement = (Element) nNode;
					for (String s : Fighter.item)
						fighter.set(s,getTagValue(s, eElement));

					toReturn.add(fighter);
				}
			}
			return toReturn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
				.getChildNodes();

		Node nValue = (Node) nlList.item(0);

		return nValue.getNodeValue();
	}

}