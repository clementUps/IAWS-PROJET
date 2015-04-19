package univ.ups.iaws.projet;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import univ.ups.iaws.Beans.Film;
import univ.ups.iaws.Beans.Salle;
import univ.ups.iaws.Hibernate.HibernateUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Created by clement on 06/04/2015.
 */
public class GestionnaireFilm {
    private Film salle;
    private static Session session;
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
        List<Film> films= (List<Film> )session.createCriteria(Film.class).add(Restrictions.eq("imdbId", newFilm.getImdbId())).list();
        if(films != null && films.size()!= 0) {
            return films.get(0);
        }
        session.saveOrUpdate(newFilm);
        return newFilm;
    }

    public List<Film> saveFilm(List<Film> newFilm){
        List<Film> listeFilm = new ArrayList<Film>();
        Film filmTempo = null;
        List<Film> films= (List<Film> )session.createCriteria(Film.class).list();
        for(Film film:newFilm){
            for(Film filmIn : films){
                System.out.println("film "+filmIn.getTitre());
                if(filmIn.getImdbId() == film.getImdbId()){
                    filmTempo = filmIn;
                }
            }
            if(filmTempo == null) {
                filmTempo = saveFilm(film);
            }
            listeFilm.add(filmTempo);
            filmTempo = null;
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
