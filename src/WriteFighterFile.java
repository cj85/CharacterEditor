import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WriteFighterFile {
	private ArrayList<Fighter> myFighters;

	public WriteFighterFile(ArrayList<Fighter> f) {
		myFighters = f;
	}

	public void creat() {

		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("Fighers");
			doc.appendChild(rootElement);
			for (Fighter f : myFighters) {
				Element fighter = doc.createElement("Fighter");
				rootElement.appendChild(fighter);

				Element name = doc.createElement("name");
				name.appendChild(doc.createTextNode(f.getName()));
				fighter.appendChild(name);

				Element head = doc.createElement("head");
				head.appendChild(doc.createTextNode(f.getHead()));
				fighter.appendChild(head);

			}
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("./Fighters.xml"));

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

			System.out.println("File saved!");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}
}