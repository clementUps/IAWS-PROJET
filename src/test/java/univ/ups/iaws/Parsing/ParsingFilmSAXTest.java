package univ.ups.iaws.Parsing;

import junit.framework.TestCase;
import univ.ups.iaws.Beans.Film;

public class ParsingFilmSAXTest extends TestCase {

    public void testExists() throws Exception {
        Film monFilm = new Film("The Matrix Reloaded", 2003, "tt0234215");
        ParsingFilmSAX parsingTest = new ParsingFilmSAX();
        assertFalse(parsingTest.exists(monFilm));
        parsingTest.listeFilm.add(monFilm);
        assertTrue(parsingTest.exists(monFilm));
        parsingTest.listeFilm.remove(monFilm);
        assertFalse(parsingTest.exists(monFilm));
    }

    public void testAfficherListeFilm() throws Exception {
        Film monFilm = new Film("The Matrix Reloaded", 2003, "tt0234215");
        ParsingFilmSAX parsingTest = new ParsingFilmSAX();
        parsingTest.listeFilm.add(monFilm);
        assertTrue(parsingTest.afficherListeFilm().
                equals("Liste :\nNom -> " + monFilm.getTitre() +
                        "\nAnnee -> " + monFilm.getAnnee() +
                        "\nimdbId -> " + monFilm.getImdbId() + "\n\n"));
    }
}