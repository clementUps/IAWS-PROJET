package univ.ups.iaws.Parsing;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import univ.ups.iaws.Beans.Film;

public class ParsingFilmSAX extends DefaultHandler {

    static ArrayList<Film> listeFilm = new ArrayList<Film>();


    public void startDocument() {
        System.out.println("*** START DOCUMENT");
    }

    public void endDocument() {
        System.out.println("*** END DOCUMENT");
    }

    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        int attNb = attributes.getLength();
        String nom = null;
        int annee = 0;
        String imdbId = null;
        for (int i = 0; i < attNb; i++) {
            if(attributes.getQName(i).equals("Title")) {
                nom = attributes.getValue(i);
            }
            if(attributes.getQName(i).equals("Year")) {
                annee = Integer.parseInt(attributes.getValue(i));
            }
            if(attributes.getQName(i).equals("imdbID")) {
                imdbId = attributes.getValue(i);
            }
            // je vérifie si c'est un film, dans ce cas je rajoute dans la liste
            if(attributes.getQName(i).equals("Type")) {
                if (attributes.getValue(i).equals("movie")) {
                    Film monFilm = new Film(nom, annee, imdbId);
                    if (!exists(monFilm)) { //il n'existe pas dans ma liste donc je l'ajoute
                        listeFilm.add(monFilm);
                    }
                }
            }
            System.out.print(attributes.getQName(i) + "_" + i + "_("
                    + attributes.getValue(i) + ") + ");
        }
    }

    // on vérifie si un film est déjà dans la liste grâce à son imdbID
    public boolean exists(Film monFilm) {
        for (Film film : listeFilm) {
            if (film.getImdbId().equals(monFilm.getImdbId())) {
                return true;
            }
        }
        return false;
    }

    public static String afficherListeFilm() {
        String stringTest = "Liste :\n";
        for (Film film : listeFilm) {
            stringTest += "Nom -> " + film.getTitre() + "\nAnnee -> " + film.getAnnee() +
                    "\nimdbId -> " + film.getImdbId() + "\n\n";
        }
        System.out.println(stringTest);
        return (stringTest); //permet de tester l'affichage
    }

    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        System.out.println(""); // juste pour passer à la ligne
    }

    public void characters(char[] ch, int start, int length)
            throws SAXException {
        System.out.print("text(" + String.copyValueOf(ch, start, length) + ")");
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
        saxParser.parse(new DataInputStream(refFic.openStream()), new ParsingFilmSAX());
        afficherListeFilm();
    }
}