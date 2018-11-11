package trainingapp;

import java.io.File;

public class TestXML {

	public static void main(String argv[]) {

		try {
			File inputFile = new File("input.xml");
			System.out.println(inputFile.exists());
			// DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			// DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			// Document doc = docBuilder.parse(inputFile);
			// Node cars = doc.getFirstChild();
			// Node supercar = doc.getElementsByTagName("supercars").item(0);
			//
			// // update supercar attribute
			// NamedNodeMap attr = supercar.getAttributes();
			// Node nodeAttr = attr.getNamedItem("company");
			// nodeAttr.setTextContent("Lamborigini");
			//
			// // loop the supercar child node
			// NodeList list = supercar.getChildNodes();
			//
			// for (int temp = 0; temp < list.getLength(); temp++) {
			// Node node = list.item(temp);
			// if (node.getNodeType() == Node.ELEMENT_NODE) {
			// Element eElement = (Element) node;
			// System.out.println(eElement.getNodeName());
			// System.out.println(eElement.getTextContent());
			// if ("carname".equals(eElement.getNodeName())) {
			// if("Ferrari 101".equals(eElement.getTextContent())) {
			// eElement.setTextContent("Lamborigini 001");
			// }
			// if("Ferrari 202".equals(eElement.getTextContent()))
			// eElement.setTextContent("Lamborigini 002");
			// }
			// }
			// }
			// NodeList childNodes = cars.getChildNodes();
			//
			// for(int count = 0; count < childNodes.getLength(); count++) {
			// Node node = childNodes.item(count);
			//
			// if("luxurycars".equals(node.getNodeName()))
			// cars.removeChild(node);
			// }
			//
			// // write the content on console
			// TransformerFactory transformerFactory = TransformerFactory.newInstance();
			// Transformer transformer = transformerFactory.newTransformer();
			// DOMSource source = new DOMSource(doc);
			// System.out.println("-----------Modified File-----------");
			// StreamResult consoleResult = new StreamResult(System.out);
			// transformer.transform(source, consoleResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}