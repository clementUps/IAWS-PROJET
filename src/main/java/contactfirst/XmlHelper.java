package contactfirst;

import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by Max on 01/04/2015.
 */
public class XmlHelper {

    /**
     * Return he dom root element of an xml file
     * @param filePathInClassPath  the file path relative to the classpath
     * @return  the root element
     * @throws javax.xml.parsers.ParserConfigurationException
     * @throws java.io.IOException
     * @throws org.xml.sax.SAXException
     */
    public static Element getRootElementFromFileInClasspath(String filePathInClassPath) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // root elements
        Document doc = docBuilder.parse(new ClassPathResource(filePathInClassPath).getInputStream());
        return doc.getDocumentElement();
    }

}
