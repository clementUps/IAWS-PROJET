package univ.ups.iaws.projet;

import RestFull.ClientFilm;
import org.hibernate.Session;
import org.xml.sax.SAXException;
import univ.ups.iaws.Beans.Film;
import univ.ups.iaws.Beans.Salle;
import univ.ups.iaws.Parsing.ParsingFilmSAX;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by clement on 19/04/2015.
 */
public class App {
    public static void main(String[] args) {
        try {


            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser saxParser = spf.newSAXParser();
            ClientFilm c = new ClientFilm();
            InputStream in = c.getEvals("The Matrix", "", false);
            ParsingFilmSAX parse = new ParsingFilmSAX();
            saxParser.parse(new DataInputStream(in), parse);
            parse.afficherListeFilm();
            Session session = GestionnaireLiaison.createSession();
            // insertion en base de donnée de Film
            GestionnaireFilm gestionnaireFilm = new GestionnaireFilm(session);
            gestionnaireFilm.saveFilm(parse.getListeFilm());
            in = c.getEvals("matrix", "", true);
            saxParser.parse(new DataInputStream(in), new ParsingFilmSAX());
            parse.afficherListeFilm();

            Salle salle = new Salle();
            salle.setNbSalle(5);
            salle.setVille("Toulouse");
            // insertion en base de donnée d'une liste de film lié a une salle
            GestionnaireLiaison gestionnaire = new GestionnaireLiaison(session);
            gestionnaire.saveAll(salle, parse.getListeFilm());
            System.out.println(gestionnaire.getSallesByCritere(new Film("The Matrix",1999,"tt0133093")).get(0).getVille());
            System.out.println(gestionnaire.getSallesByCritere(parse.getListeFilm().get(0),5).get(0).getVille());
            System.out.println(gestionnaire.getSallesByCritere(parse.getListeFilm().get(0),"Toulouse").get(0).getVille());
            System.out.println(gestionnaire.getSallesByCritere("Toulouse").get(0).getVille());
            in.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
