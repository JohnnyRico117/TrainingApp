package trainingapp;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class AddXmlNode {
	public static void main(String[] args) throws Exception {

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse("server.xml");
		Element root = document.getDocumentElement();

		Collection<Server> servers = new ArrayList<Server>();
		servers.add(new Server());

		for (Server server : servers) {
			// server elements
			Element newServer = document.createElement("server");

			Element name = document.createElement("name");
			name.appendChild(document.createTextNode(server.getName()));
			newServer.appendChild(name);

			Element port = document.createElement("port");
			port.appendChild(document.createTextNode(Integer.toString(server.getPort())));
			newServer.appendChild(port);

			root.appendChild(newServer);
		}

		DOMSource source = new DOMSource(document);

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		StreamResult result = new StreamResult("server.xml");
		transformer.transform(source, result);
	}

	public static class Server {
		public String getName() {
			return "foo";
		}

		public Integer getPort() {
			return 12345;
		}
	}
}