package trainingapp;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
// TransformerFactory, Transformer
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
// DOMSource
import javax.xml.transform.dom.DOMSource;
// StreamResult
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * @author clecon
 * 
 */
public class XMLCreateAddressbook {

	/**
	 * Schreibt das XML-Dokument in eine Datei.
	 * 
	 * @param doc
	 *            XML-Dokument.
	 * @param filename
	 *            Name der zu schreibenden XML-Datei.
	 */
	public void writeXMLDocment(Document doc, String filename) {
		File file = new File(filename);

		try {

			TransformerFactory transFactory = TransformerFactory.newInstance();
			Transformer transformer = transFactory.newTransformer();

			// Source und Result setzen:
			Source source = new DOMSource(doc);
			Result result = new StreamResult(file);

			// NEU
			// Eigenschaften setzen
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			// transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "-//W3C//DTD SVG
			// 1.1//EN");
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "adressen");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");

			// Dokument in Datei speichern:
			transformer.transform(source, result);

		} catch (TransformerException e1) {
			e1.printStackTrace();
		} // catch
	} // writeXMLDocument

	/**
	 * 
	 * @param doc
	 *            XML-Dokument, in das eine Adresse eingef&uuml;gt werden soll.
	 * @param name
	 *            Nachname.
	 * @param vorname
	 *            Vorname (Rufname).
	 * @param telefonVorwahl
	 *            Telefon-Vorwahl.
	 * @param telefon
	 *            Telefonnummer.
	 * @param email
	 *            Email.
	 * @param strasseBez
	 *            Stra&szlig;enname.
	 * @param strasseNr
	 *            Hausnummer.
	 * @param ort
	 *            Ort.
	 * @return <tt>adresse></tt>-Element.
	 */
	protected Element createAddress(Document doc, String name, String vorname, String telefonVorwahl, String telefon,
			String email, String strasseBez, String strasseNr, String ort) {
		Text txtName, txtVorname, txtTelefon, txtEmail, txtStrasseBez, txtStrasseNr, txtOrt;

		Element addr = doc.createElement("adresse");

		// Name: Inhalt befindet sich in #PCDATA:
		Element elName = doc.createElement("name");
		txtName = doc.createTextNode(name);
		elName.appendChild(txtName);
		elName.setNodeValue(name);

		// Vorname: Inhalt befindet sich in #PCDATA:
		Element elVorname = doc.createElement("vorname");
		txtVorname = doc.createTextNode(vorname);
		elVorname.appendChild(txtVorname);

		// Beim Telefon-Element Vorwahl als Attribut eintragen,
		// Rufnummer ist #PCDATA:
		Element elTelefon = doc.createElement("telefon");
		txtTelefon = doc.createTextNode(telefon);
		elTelefon.appendChild(txtTelefon);
		elTelefon.setAttribute("vorwahl", telefonVorwahl);

		// Email: Inhalt befindet sich in #PCDATA:
		Element elEmail = doc.createElement("email");
		txtEmail = doc.createTextNode(email);
		elEmail.appendChild(txtEmail);

		// Strasse besteht aus Bezeichnung und Hausnummer:
		Element elStrasse = doc.createElement("strasse");
		Element elStrasseBez = doc.createElement("bez");
		txtStrasseBez = doc.createTextNode(strasseBez);
		elStrasseBez.appendChild(txtStrasseBez);
		Element elStrasseNr = doc.createElement("nr");
		txtStrasseNr = doc.createTextNode(strasseNr);
		elStrasseNr.appendChild(txtStrasseNr);
		elStrasse.appendChild(elStrasseBez);
		elStrasse.appendChild(elStrasseNr);

		// Ort: Inhalt befindet sich in #PCDATA:
		Element elOrt = doc.createElement("ort");
		txtOrt = doc.createTextNode(ort);
		elOrt.appendChild(txtOrt);

		addr.appendChild(elName);
		addr.appendChild(elVorname);
		addr.appendChild(elTelefon);
		addr.appendChild(elEmail);
		addr.appendChild(elStrasse);
		addr.appendChild(elOrt);
		return addr;
	} // createAddress

	/**
	 * Erzeugt ein XML-Dokument
	 * 
	 * @param doc
	 *            Neu erstelltes XML-Dokument.
	 * @return Modifiziertes XML-Dokument.
	 */
	public Document generateXML(Document doc) {
		Element xml;
		Element doctype;
		Element adrRoot;
		Element addr1, addr2;

		adrRoot = doc.createElement("adressen");

		doc.appendChild(adrRoot);

		// Adressen einfuegen:
		addr1 = createAddress(doc, "Jagodsen", "Henni", "01234", "4711", "ich@jagodsen.net", "Flutstraße", "44",
				"Dünenhausen");
		adrRoot.appendChild(addr1);
		addr2 = createAddress(doc, "Huibuh", "Spensti", "99887", "121212", "gespenst@schloss.edu", "Versteckte Straße",
				"0", "Heimlichhausen");
		adrRoot.appendChild(addr2);

		// Element ersetzen:
		// Neuer Textknoten:
		Text newTextNode = doc.createTextNode("Uferdorf");

		// ort-Elemente im Dokument suchen:
		NodeList nList = doc.getElementsByTagName("ort");
		// Alle ort-Elemente durchsuchen:
		for (int i = 0; i < nList.getLength(); i++) {
			Element el = (Element) nList.item(i);
			// Text-Knoten ermitteln:
			Text oldTextNode = (Text) el.getFirstChild();
			// Text-Knoten ggf. ersetzen:
			if (oldTextNode.getData().equals("Dünenhausen")) {
				el.replaceChild(newTextNode, oldTextNode);
			} // if
		} // for

		// Element ergänzen:
		nList = doc.getElementsByTagName("name");
		// Alle ort-Elemente durchsuchen:
		for (int i = 0; i < nList.getLength(); i++) {
			Element el = (Element) nList.item(i);
			// Text-Konten ermitteln:
			Text textNode = (Text) el.getFirstChild();
			// Text-Knoten lesen:
			if (textNode.getData().equals("Huibuh")) {
				// Neues telefon-Element einfügen
				// (mit den Attributen "vorwahl" und "art")
				Element newTelefon = doc.createElement("telefon");
				Text txtTelefon2 = doc.createTextNode("667788");
				newTelefon.appendChild(txtTelefon2);
				newTelefon.setAttribute("vorwahl", "0111");
				newTelefon.setAttribute("art", "Mobil");
				el.appendChild(newTelefon);
			} // if
		} // for

		// Attribut ergänzen:
		nList = doc.getElementsByTagName("vorname");
		// Alle vorname-Elemente durchsuchen:
		for (int i = 0; i < nList.getLength(); i++) {
			Element el = (Element) nList.item(i);
			// Text-Knonten ermitteln:
			Text textNode = (Text) el.getFirstChild();
			// Text-Knoten lesen:
			if (textNode.getData().equals("Spensti")) {
				// Attribut einfügen
				el.setAttribute("spitzname", "ja");
			} // if
		} // for

		// Element löschen:
		nList = doc.getElementsByTagName("telefon");
		// Alle telefon-Elemente durchsuchen:
		for (int i = 0; i < nList.getLength(); i++) {
			Element el = (Element) nList.item(i);
			// Handy?
			if (el.getAttribute("art").equals("Mobil")) {
				// dieses Element löschen,
				// dazu das entsprechende Eltern-Element bestimmen:
				el.getParentNode().removeChild(el);
			} // if
		} // for

		return doc;
	} // generateXML

	/**
	 * Erstellung eines neuen XML-Dokuments als Instanz der Klasse
	 * <tt>Document</tt>.
	 * 
	 * @return <tt>Document</tt>-Objekt als Repräsentant eines XML-Dokuments.
	 */
	public Document getXMLDocument() {
		Document document = null;

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			// XML-Dokument erstellen
			document = builder.newDocument();
			document.setXmlStandalone(true);
		} catch (ParserConfigurationException e) {
			System.err.println("Fehler beim Erstellen " + " des XML-Dokuments!");
			e.printStackTrace();
		} // catch

		return document;
	} // getXMLDocument

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		XMLCreateAddressbook xmlDemo = new XMLCreateAddressbook();
		Document doc = xmlDemo.getXMLDocument();
		xmlDemo.generateXML(doc);
		xmlDemo.writeXMLDocment(doc, "myadress.xml");
		System.out.println("Datei myadress.xml wurde erzeugt.");

	} // main

} // class XMLCreateAddressbook