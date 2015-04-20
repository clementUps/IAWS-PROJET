package univ.ups.iaws.projet;

import junit.framework.TestCase;
import org.hibernate.Session;
import univ.ups.iaws.Beans.Film;
import univ.ups.iaws.Beans.FilmSalle;
import univ.ups.iaws.Beans.Salle;

import java.util.ArrayList;
import java.util.List;

public class GestionnaireLiaisonTest extends TestCase {
    Session session = GestionnaireLiaison.createSession();
    GestionnaireLiaison gestion = new GestionnaireLiaison(session);
    Salle salle1, salle2, salle3, salle4, salle5, salle6, salle7;
    Film film1, film2, film3, film4, film5, film6, film7;
    FilmSalle filmSalle;

    private void init() {
        film1 = new Film("test1", 2001, "azerty1");
        film2 = new Film("test2", 2002, "azerty1");
        film3 = new Film("test3", 2003, "azerty3");
        film4 = new Film("test4", 2004, "azerty3");
        film5 = new Film("test5", 2005, "azerty5");
        film6 = new Film("test6", 2006, "azerty6");
        film7 = new Film("test7", 2007, "azerty7");
        salle1 = new Salle("Toulouse1", 1);
        salle2 = new Salle("Toulouse2", 2);
        salle3 = new Salle("Toulouse3", 3);
        salle4 = new Salle("Toulouse4", 4);
        salle5 = new Salle("Toulouse5", 5);
        salle6 = new Salle("Toulouse6", 6);
        salle7 = new Salle("Toulouse7", 7);
        List<Film> maListe = new ArrayList<Film>();
        maListe.add(film1);
        filmSalle = gestion.saveAll(salle1, maListe);
    }

    public void testSaveAll() throws Exception {
        init();
        assertTrue(gestion.getSession().contains(salle1));
        assertTrue(gestion.getSession().contains(filmSalle));
    }

    public void testGetSallesByCritereFilm() throws Exception {
        init();
        List<Salle> listeS = gestion.getSallesByCritere(film1);
        assertTrue(listeS.contains(salle1));
    }

    public void testGetSallesByCritereNbSalles() throws Exception {
        init();
        List<Salle> listeF = gestion.getSallesByCritere(1);
        assertTrue(listeF.contains(salle1));
    }

    public void testGetSallesByCritereVille() throws Exception {
        init();
        List<Salle> listeS = gestion.getSallesByCritere("Toulouse1");
        assertTrue(listeS.contains(salle1));
    }

    public void testGetSallesByCritereNbSallesVille() throws Exception {
       /* init();
        List<Salle> listeS = gestion.getSallesByCritere(1, "Toulouse1");
        assertTrue(listeS.contains(salle1));*/
    }

    public void testGetSallesByCritereFilmNbSalles() throws Exception {
        init();
        List<Salle> listeS = gestion.getSallesByCritere(film1, 1);
        assertTrue(listeS.contains(salle1));
    }

    public void testGetSallesByCritereFilmVille() throws Exception {
        init();
        List<Salle> listeS = gestion.getSallesByCritere(film1, "Toulouse1");
        assertTrue(listeS.contains(salle1));
    }

    public void testGetSallesByCritereAll() throws Exception {
        init();
        List<Salle> listeS = gestion.getSallesByCritere(film1, 1, "Toulouse1");
        assertTrue(listeS.contains(salle1));
    }

    public void testGetFilmsBySalle() throws Exception {
        /*init();
        List<Film> listeF = gestion.getFilmsBySalle(salle1);
        System.out.println(listeF);
        assertTrue(listeF.contains(film1));*/
    }

    public void testDeleteElement() throws Exception {
        /*init();
        gestion.deleteElement(salle1, film1);
        assertFalse(gestion.getSession().contains(salle1));
        assertFalse(gestion.getSession().contains(film1));*/
    }
}