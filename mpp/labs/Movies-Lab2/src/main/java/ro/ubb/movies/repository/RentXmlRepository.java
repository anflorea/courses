package main.java.ro.ubb.movies.repository;

import main.java.ro.ubb.movies.domain.Movie;
import main.java.ro.ubb.movies.domain.MovieClient;
import main.java.ro.ubb.movies.domain.genreType;
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
public class RentXmlRepository extends InMemoryRepository<Integer, MovieClient>{
    private String fileName;
    private XmlUtil utils;

    public RentXmlRepository(Validator<MovieClient> validator, String fileName) {
        super(validator);
        this.fileName = fileName;
        utils = new XmlUtil(fileName);

        loadData();
    }

    public void loadData()  {
        Document document = utils.loadDocument();
        Node root = document.getDocumentElement();
        NodeList nodeList = root.getChildNodes();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {   //node instanceof Element
                Element element = (Element) node;
                MovieClient entity = createUser(element);


                super.save(entity);
            }
        }


    }

    public void writeToFile() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            // root element
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("rentals");
            doc.appendChild(rootElement);
            for (MovieClient x : super.findAll()) {
                Element entity = doc.createElement("rental");
                rootElement.appendChild(entity);
                entity.setAttribute("id", x.getId().toString());
                appendElement(doc, "clientId", Integer.toString(x.getMovieID()), entity);
                appendElement(doc, "movieId", Integer.toString(x.getClientID()), entity);

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

    private MovieClient createUser(Element element) {
        String id = element.getAttributeNode("id").getValue();
        String clientId = element.getElementsByTagName("clientId").item(0).getTextContent();
        String movieId = element.getElementsByTagName("movieId").item(0).getTextContent();

        return new MovieClient(Integer.parseInt(id), Integer.parseInt(clientId), Integer.parseInt(movieId));
    }

    @Override
    public Optional<MovieClient> delete(Integer id) {
        super.delete(id);
        writeToFile();
        return Optional.empty();
    }

    @Override
    public Optional<MovieClient> save(MovieClient entity){
        super.save(entity);
        writeToFile();
        return Optional.empty();
    }

    @Override
    public Optional<MovieClient> update(MovieClient entity){
        super.update(entity);
        writeToFile();
        return Optional.empty();
    }

}
