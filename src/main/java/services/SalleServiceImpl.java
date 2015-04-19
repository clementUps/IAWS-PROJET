package services;

import org.hibernate.Session;
import univ.ups.iaws.Beans.Film;
import univ.ups.iaws.Beans.Salle;
import univ.ups.iaws.projet.GestionnaireLiaison;

import java.util.List;

/**
 * Created by Max on 17/04/2015.
 */
public class SalleServiceImpl implements SalleService{
    @Override
    public List<Salle> findAllSalle(String imdbID) {
        Session session = GestionnaireLiaison.createSession();
        List<Salle> salle = null;
        GestionnaireLiaison gestionnaireLiaison = new GestionnaireLiaison(session);
        try {
            salle = gestionnaireLiaison.getSallesByCritere(new Film("", 0, imdbID));
        }catch (Exception e){
            e.printStackTrace();
        }
        return salle;
    }

}
