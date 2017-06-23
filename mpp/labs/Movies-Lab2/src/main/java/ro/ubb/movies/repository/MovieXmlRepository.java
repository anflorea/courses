package main.java.ro.ubb.movies.repository;

import main.java.ro.ubb.movies.domain.Movie;
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
public class MovieXmlRepository extends InMemoryRepository<Integer, Movie> {

    private String fileName;
    private XmlUtil utils;

    public MovieXmlRepository(Validator<Movie> validator, String fileName) {
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
                Movie movie = createUser(element);
                super.save(movie);
            }
        }


    }

    public void writeToFile() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            // root element
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("movies");
            doc.appendChild(rootElement);
            for (Movie x : super.findAll()) {
                Element entity = doc.createElement("movie");
                rootElement.appendChild(entity);
                entity.setAttribute("id", x.getId().toString());
                appendElement(doc, "name", x.getName(), entity);
                appendElement(doc, "genre", x.getGenre().name(), entity);
                appendElement(doc, "year", Integer.toString(x.getYear()), entity);

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

    private Movie createUser(Element element) {
        String id = element.getAttributeNode("id").getValue();
        String name = element.getElementsByTagName("name").item(0).getTextContent();
        genreType genre = genreType.valueOf(element.getElementsByTagName("genre").item(0).getTextContent());
        Integer year = Integer.parseInt(element.getElementsByTagName("year").item(0).getTextContent());
        return new Movie(Integer.parseInt(id), name, genre, year);
    }

    @Override
    public Optional<Movie> delete(Integer id) {
        super.delete(id);
        writeToFile();
        return Optional.empty();
    }

    @Override
    public Optional<Movie> save(Movie entity){
        super.save(entity);
        writeToFile();
        return Optional.empty();
    }

    @Override
    public Optional<Movie> update(Movie entity){
        super.update(entity);
        writeToFile();
        return Optional.empty();
    }




}
