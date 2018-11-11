package trainingapp;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class XMLParser {

	public Document getXMLDocument() throws ParserConfigurationException {
		Document document = null;

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		document = builder.newDocument();
		document.setXmlStandalone(true);

		return document;
	}

	public File writeXMLDocment(Document doc, String filename) throws TransformerException {
		File file = new File(filename);

		TransformerFactory transFactory = TransformerFactory.newInstance();
		Transformer transformer = transFactory.newTransformer();

		Source source = new DOMSource(doc);
		Result result = new StreamResult(file);

		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "zutaten");
		transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		transformer.transform(source, result);

		return file;
	}

	public static Element createZutat(Document doc, String name, String menge, String kalorien, String fett,
			String carbs, String eiweiss) {

		Text txtName, txtMenge, txtKalorien, txtFett, txtCarbs, txtEiweiss;

		Element zutat = doc.createElement("zutat");

		// Name
		Element elName = doc.createElement("name");
		txtName = doc.createTextNode(name);
		elName.appendChild(txtName);
		elName.setNodeValue(name);

		// Menge
		Element elMenge = doc.createElement("menge");
		txtMenge = doc.createTextNode(menge);
		elMenge.appendChild(txtMenge);
		elMenge.setNodeValue(menge);

		// Kalorien
		Element elKalorien = doc.createElement("kalorien");
		txtKalorien = doc.createTextNode(kalorien);
		elKalorien.appendChild(txtKalorien);
		elKalorien.setNodeValue(kalorien);

		// Fett
		Element elFett = doc.createElement("fett");
		txtFett = doc.createTextNode(fett);
		elFett.appendChild(txtFett);
		elFett.setNodeValue(fett);

		// Kohlenhydrate
		Element elCarbs = doc.createElement("carbs");
		txtCarbs = doc.createTextNode(carbs);
		elCarbs.appendChild(txtCarbs);
		elCarbs.setNodeValue(carbs);

		// Eiweiss
		Element elEiweiss = doc.createElement("eiweiss");
		txtEiweiss = doc.createTextNode(eiweiss);
		elEiweiss.appendChild(txtEiweiss);
		elEiweiss.setNodeValue(eiweiss);

		zutat.appendChild(elName);
		zutat.appendChild(elMenge);
		zutat.appendChild(elKalorien);
		zutat.appendChild(elFett);
		zutat.appendChild(elCarbs);
		zutat.appendChild(elEiweiss);

		return zutat;
	}

	public void getData() throws Exception {

		File file = new File("XML/zutaten.xml");
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;

		docBuilder = docFactory.newDocumentBuilder();

		Document doc = docBuilder.parse(file);

		NodeList zutaten = doc.getElementsByTagName("zutat");
		Node zutat1 = zutaten.item(0);

		NodeList inhalt = zutat1.getChildNodes();

		for (int i = 0; i < inhalt.getLength(); i++) {
			Node node = inhalt.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) node;
				System.out.println(eElement.getNodeName());
				System.out.println(eElement.getTextContent());
			}
		}
	}

	public static void main(String[] args) throws Exception {

		XMLParser xmlparser = new XMLParser();
		File zutatenxml = new File("XML/zutaten.xml");

		if (!zutatenxml.exists()) {
			Document doc = xmlparser.getXMLDocument();
			Element root = doc.createElement("zutaten");
			doc.appendChild(root);
			zutatenxml = xmlparser.writeXMLDocment(doc, "XML/zutaten.xml");
		}

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		Document doc = dbFactory.newDocumentBuilder().parse(zutatenxml);

		if (doc != null) {

			// Testzutat
			Element zutat1 = createZutat(doc, "Spinat", "100", "54", "3.3", "2.6", "3.5");
			Element root = doc.getDocumentElement();
			root.appendChild(zutat1);

			System.out.println("YAY");
		}

		DOMSource source = new DOMSource(doc);

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		StreamResult result = new StreamResult("XML/zutaten.xml");
		transformer.transform(source, result);

	}
}
