package univ.ups.iaws.projet;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ParsingFilmSAX extends DefaultHandler {



    public void startDocument() {
        System.out.println("*** START DOCUMENT");
    }

    public void endDocument() {
        System.out.println("*** END DOCUMENT");
    }

    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        int attNb = attributes.getLength();
        System.out.print(qName + "= ");
    }

    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        System.out.println(""); // juste pour passer à la ligne
    }

    public void characters(char[] ch, int start, int length)
            throws SAXException {
        String text = String.copyValueOf(ch, start, length);
        System.out.print("text(" + text + ")");
    }

    /**
     * @param args
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws IOException
     */
    public static void main(String[] args) throws ParserConfigurationException,
            SAXException, IOException {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser saxParser = spf.newSAXParser();
        URL refFic = new URL("http://www.omdbapi.com/?s=matrix&y=&plot=short&r=xml");
        saxParser.parse(new DataInputStream(refFic.openStream()),new ParsingFilmSAX());
    }
}