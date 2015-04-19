package univ.ups.iaws.projet;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import univ.ups.iaws.Beans.Film;
import univ.ups.iaws.Beans.Salle;
import univ.ups.iaws.Hibernate.HibernateUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by clement on 06/04/2015.
 */
public class GestionnaireFilm {
    private Film salle;
    private Session session;
    private HibernateUtils hibernate;

    public GestionnaireFilm(){
        HibernateUtils hibernate = new HibernateUtils();
        SessionFactory sessionFactory = hibernate.getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
    }
    public GestionnaireFilm(Session session){
        this.session = session;
    }
    public Film saveFilm(Film newFilm){
        session.saveOrUpdate(newFilm);
        return newFilm;
    }

    public List<Film> saveFilm(List<Film> newFilm){
        List<Film> listeFilm = new ArrayList<Film>();
        Film filmTempo;
        for(Film film:newFilm){
            filmTempo = saveFilm(film);
            listeFilm.add(filmTempo);
        }
        return listeFilm;
    }


    public void deleteFilm(Film film){
        session.delete(film);
    }
    public void shutDownFactory(){
        session.close();
        hibernate.shutdown();
    }

}
