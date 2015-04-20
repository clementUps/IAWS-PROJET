package univ.ups.iaws.projet;

import junit.framework.TestCase;
import univ.ups.iaws.Beans.Salle;

import java.util.ArrayList;
import java.util.List;

public class GestionnaireSalleTest extends TestCase {
    GestionnaireSalle gestion = new GestionnaireSalle();
    Salle salle1, salle2, salle3, salle4, salle5, salle6, salle7;


    private void init() {
        salle1 = new Salle("Toulouse1", 1);
        salle2 = new Salle("Toulouse2", 2);
        salle3 = new Salle("Toulouse3", 3);
        salle4 = new Salle("Toulouse4", 4);
        salle5 = new Salle("Toulouse5", 5);
        salle6 = new Salle("Toulouse6", 6);
        salle7 = new Salle("Toulouse7", 7);
    }

    public void testSaveSalle() throws Exception {
        init();
        assertFalse(gestion.getSession().contains(salle1));

        gestion.saveSalle(salle1);
        assertTrue(gestion.getSession().contains(salle1));

        gestion.saveSalle(salle2);
        assertTrue(gestion.getSession().contains(salle2));

        salle5 = null;
        gestion.saveSalle(salle5); //cela ne doit pas générer d'exceptions
    }

    public void testSaveSalle1() throws Exception {
        init();
        List<Salle> maListe = new ArrayList<Salle>();
        maListe.add(salle3);
        gestion.saveSalle(maListe);
        assertTrue(gestion.getSession().contains(salle3));

        maListe.add(salle4);
        gestion.saveSalle(maListe);
        assertTrue(gestion.getSession().contains(salle4));

        salle5 = null;
        maListe.add(salle5);
        gestion.saveSalle(maListe); //cela ne doit pas générer d'exceptions
    }

    public void testDeleteSalle() throws Exception {
        init();
        gestion.saveSalle(salle6);
        gestion.saveSalle(salle7);
        gestion.deleteSalle(salle6);
        assertFalse(gestion.getSession().contains(salle6));
        assertTrue(gestion.getSession().contains(salle7));

        salle5 = null;
        gestion.deleteSalle(salle5);//cela ne doit pas générer d'exceptions
    }
}