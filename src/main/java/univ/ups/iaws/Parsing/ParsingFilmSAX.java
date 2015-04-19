package univ.ups.iaws.Parsing;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import RestFull.ClientFilm;
import org.apache.commons.io.IOUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import univ.ups.iaws.Beans.Film;
import univ.ups.iaws.Beans.Salle;
import univ.ups.iaws.projet.GestionnaireFilm;
import univ.ups.iaws.projet.GestionnaireLiaison;

public class ParsingFilmSAX extends DefaultHandler {

    static ArrayList<Film> listeFilm = new ArrayList<Film>();

    public List<Film> getListeFilm(){
        return listeFilm;
    }
    public void startDocument() { }

    public void endDocument() { }

    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        int attNb = attributes.getLength();
        String nom = null;
        int annee = 0;
        String imdbId = null;
        for (int i = 0; i < attNb; i++) {
            if (attributes.getQName(i).toLowerCase().equals("title")) {
                nom = attributes.getValue(i);
            }
            if (attributes.getQName(i).toLowerCase().equals("year")) {
                try {
                    annee = Integer.parseInt(attributes.getValue(i));
                }catch(NumberFormatException e) {
                    if(attributes.getValue(i).matches("-\\/")){
                        annee = Integer.parseInt(attributes.getValue(i).split("-\\/")[0]);
                    }else {
                        annee = -1;
                    }
                }
            }
            if (attributes.getQName(i).toLowerCase().equals("imdbid")) {
                imdbId = attributes.getValue(i);
            }
            // je vérifie si c'est un film, dans ce cas je rajoute dans la liste
            if (attributes.getQName(i).toLowerCase().equals("type")) {
                if (attributes.getValue(i).equals("movie")) {
                    Film monFilm = new Film(nom, annee, imdbId);
                    if (!exists(monFilm)) { //il n'existe pas dans ma liste donc je l'ajoute
                        listeFilm.add(monFilm);
                    }
                }
            }
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
    }

    public void characters(char[] ch, int start, int length)
            throws SAXException {
        System.out.print("text(" + String.copyValueOf(ch, start, length) + ")");
    }

}