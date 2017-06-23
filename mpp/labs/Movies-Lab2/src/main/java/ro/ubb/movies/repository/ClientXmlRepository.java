package main.java.ro.ubb.movies.repository;

import main.java.ro.ubb.movies.domain.Client;
import main.java.ro.ubb.movies.domain.validators.Validator;
import main.java.ro.ubb.movies.util.XmlUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.util.Optional;

/**
 * Created by andrapop on 2017-03-18.
 */
public class ClientXmlRepository extends InMemoryRepository<Integer,Client> {

    private String fileName;
    private XmlUtil utils;



    public ClientXmlRepository(Validator<Client> validator, String fileName) {
        super(validator);
        this.fileName = fileName;
        utils = new XmlUtil(fileName);

        loadData();
    }


    public void loadData()  {
        Document document = utils.loadDocument();
        Node root = document.getDocumentElement();
        NodeList nodeList = root.getChildNodes();
        //List<Client> clients = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {   //node instanceof Element
                Element element = (Element) node;
                Client client = createUser(element);
                super.save(client);
            }
        }


    }

    public void writeToFile() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            // root element
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("clients");
            doc.appendChild(rootElement);
            for (Client x : super.findAll()) {
                Element client = doc.createElement("client");
                rootElement.appendChild(client);
                client.setAttribute("id", x.getId().toString());
                appendElement(doc, "firstName", x.getFirstName(), client);
                appendElement(doc, "lastName", x.getLastName(), client);
                appendElement(doc, "phoneNr", x.getPhoneNr(), client);
                appendElement(doc, "address", x.getAddress(), client);
            }

            utils.saveDocument(doc);
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        }
    }


    private static void appendElement(Document doc, String tagName, String textNode, Element userNode) {
        Element element = doc.createElement(tagName);
        element.appendChild(doc.createTextNode(textNode));
        userNode.appendChild(element);
    }

    private Client createUser(Element element) {
        String id = element.getAttributeNode("id").getValue();
        String firstName = element.getElementsByTagName("firstName").item(0).getTextContent();
        String lastName = element.getElementsByTagName("lastName").item(0).getTextContent();
        String phoneNr = element.getElementsByTagName("phoneNr").item(0).getTextContent();
        String address = element.getElementsByTagName("address").item(0).getTextContent();
        return new Client(Integer.parseInt(id), firstName, lastName, phoneNr, address);
    }



    @Override
    public Optional<Client> delete(Integer id) {
        super.delete(id);
        writeToFile();
        return Optional.empty();
    }

    @Override
    public Optional<Client> save(Client client){
        super.save(client);
        writeToFile();
        return Optional.empty();
    }

    @Override
    public Optional<Client> update(Client client){
        super.update(client);
        writeToFile();
        return Optional.empty();
    }

}
