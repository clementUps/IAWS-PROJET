package univ.ups.iaws.projet;

import junit.framework.TestCase;
import univ.ups.iaws.Beans.Film;

import java.util.ArrayList;
import java.util.List;

public class GestionnaireFilmTest extends TestCase {
    GestionnaireFilm gestion = new GestionnaireFilm();
    Film film1, film2, film3, film4, film5, film6, film7;

    private void init() {
        film1 = new Film("test1", 2001, "azerty1");
        film2 = new Film("test2", 2002, "azerty1");
        film3 = new Film("test3", 2003, "azerty3");
        film4 = new Film("test4", 2004, "azerty3");
        film5 = new Film("test5", 2005, "azerty5");
        film6 = new Film("test6", 2006, "azerty6");
        film7 = new Film("test7", 2007, "azerty7");
    }

    public void testSaveFilm() throws Exception {
        init();
        assertFalse(gestion.getSession().contains(film1));

        gestion.saveFilm(film1);
        assertTrue(gestion.getSession().contains(film1));

        gestion.saveFilm(film2); //film2 ayant le meme imdbID que film1, il n'est pas save
        assertFalse(gestion.getSession().contains(film2));

        film5 = null;
        gestion.saveFilm(film5); //cela ne doit pas générer d'exceptions
    }

    public void testSaveFilm1() throws Exception {
        init();
        List<Film> maListe = new ArrayList<Film>();
        maListe.add(film3);
        gestion.saveFilm(maListe);
        assertTrue(gestion.getSession().contains(film3));

        maListe.add(film4);//film4 ayant le meme imdbID que film3, il n'est pas save
        gestion.saveFilm(maListe);
        assertFalse(gestion.getSession().contains(film4));

        film5 = null;
        maListe.add(film5);
        gestion.saveFilm(maListe); //cela ne doit pas générer d'exceptions
    }

    public void testDeleteFilm() throws Exception {
        init();
        gestion.saveFilm(film6);
        gestion.saveFilm(film7);
        gestion.deleteFilm(film6);
        assertFalse(gestion.getSession().contains(film6));
        assertTrue(gestion.getSession().contains(film7));

        film5 = null;
        gestion.deleteFilm(film5);//cela ne doit pas générer d'exceptions
    }
}