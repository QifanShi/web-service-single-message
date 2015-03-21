package SpyOperationUtil;

import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * SypMessage parses XML String from client request, converting the string to
 * normalized XML document and then response to requested operation in
 * XML.Operations are includes add a new spy, delete an spy from current spy
 * list, return spy list in normal string and return spy list in XML string.
 *
 * @author Qifan Shi
 * @version 1.0 Last Modified: 10/10/2014
 */
public class SpyMessage {

    private String XMLString;
    private Document spyDoc;

    private String root;
    private String operation;

    private Spy spy;

    public SpyMessage(String XMLString) {
        this.XMLString = XMLString;
        spy = new Spy();
    }

    /**
     * converts an XML string into a document object.
     *
     * @param xmlString an XML string from client.
     * @return an document object contains the XML string information
     */
    private Document getDocument() {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        try {
            builder = factory.newDocumentBuilder();
            spyDoc = builder.parse(new InputSource(new StringReader(XMLString)));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return spyDoc;
    }

    /**
     * parse the XML string from client by converting an XML string to a
     * document object and then extract operation type and spy information from
     * the document object.
     */
    public void parseXML() {
        SpyMessage sm = new SpyMessage(XMLString);

        // use the result string to hold operation feedback to client.
        String result = "";

        // convert an XML string into a document
        spyDoc = sm.getDocument();

        // construct a tree structure of all information
        spyDoc.getDocumentElement().normalize();

        // extract each piece from the tree.
        root = spyDoc.getDocumentElement().getNodeName();
        operation = getElementContent("operation");

        spy.setName(getElementContent("name"));
        spy.setTitle(getElementContent("spyTitle"));
        spy.setLocation(getElementContent("location"));
        spy.setPassword(getElementContent("password"));

    }

    /**
     *
     * @param tagName the name in XML markup to identify each piece of
     * information.
     * @return the value wrapped in each tag.
     */
    private String getElementContent(String tagName) {
        NodeList nl = spyDoc.getElementsByTagName(tagName);
        Node n = nl.item(0);
        if (n != null) {
            return n.getTextContent();
        } else {
            return "";
        }
    }

    public String getXMLString() {
        return XMLString;
    }

    public Document getSpyDoc() {
        return spyDoc;
    }

    public String getRoot() {
        return root;
    }

    public String getOperation() {
        return operation;
    }

    public Spy getSpy() {
        return spy;
    }

}
