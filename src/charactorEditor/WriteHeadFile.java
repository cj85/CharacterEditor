package charactorEditor;
/*import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WriteHeadFile {
	private ArrayList<Head> myHeads;

	public WriteHeadFile(ArrayList<Head> h) {
		myHeads = h;
	}

	public void creat() {

		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("Heads");
			doc.appendChild(rootElement);
			for (Head h : myHeads) {
				Element head = doc.createElement("Head");
				rootElement.appendChild(head);

				Element name = doc.createElement("name");
				name.appendChild(doc.createTextNode(h.getName()));
				head.appendChild(name);

				Element property = doc.createElement("property");
				property.appendChild(doc.createTextNode(h.getProperty()));
				head.appendChild(property);

			}
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("./Heads.xml"));

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
}*/