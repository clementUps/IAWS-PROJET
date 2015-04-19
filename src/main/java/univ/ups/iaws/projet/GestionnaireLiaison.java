package univ.ups.iaws.projet;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import univ.ups.iaws.Beans.Film;
import univ.ups.iaws.Beans.FilmSalle;
import univ.ups.iaws.Beans.Salle;
import univ.ups.iaws.Hibernate.HibernateUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by clement on 06/04/2015.
 */
public class GestionnaireLiaison {
    private Salle salle;
    private List<Film> filmListe = new ArrayList<Film>();

    public void saveAll(Salle newSalle,List<Film> films){
        FilmSalle filmSalle;
        Film filmTempo;
        Salle salleTempo;
        HibernateUtils hibernate = new HibernateUtils();
        SessionFactory sessionFactory = hibernate.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        GestionnaireSalle gestSalle = new GestionnaireSalle(session);
        GestionnaireFilm gestFilm = new GestionnaireFilm(session);
        salle = gestSalle.saveSalle(newSalle);

        for(Film film : films){
            System.out.println(" je passe ici");
            filmTempo = gestFilm.saveFilm(film);
            filmListe.add(filmTempo);
            filmSalle = new FilmSalle();
            filmSalle.setIdFilm(filmTempo.getId());
            filmSalle.setIdSalle(salle.getId());
            session.saveOrUpdate(filmSalle);
        }
        session.flush();
        session.getTransaction().commit();
        session.close();
        //hibernate.shutdown();
    }

    public List<Salle> getSallesByFilm(Film film){
        List<Salle> listeSalle = new ArrayList<Salle>();
        HibernateUtils hibernate = new HibernateUtils();
        SessionFactory sessionFactory = hibernate.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();


        Criteria criteria = session.createCriteria(FilmSalle.class);
        criteria.add(Restrictions.eq("idFilm",film.getId()));
        criteria.setProjection(Projections.property("idSalle"));
        List result = criteria.list();
        Iterator iter = result.iterator();
        while (iter.hasNext()) {
            Integer id = (Integer) iter.next();
            System.out.println("salle : " + result.get(1));
            String queryStr = "select s from Salle s where "
                    + "s.id =:salleFilmId";
            Query query = session.createQuery(queryStr);
            query.setParameter("salleFilmId", id);
            for (Salle salle : (List<Salle>) (query.list())) {
                listeSalle.add(salle);
            }
        }

        return listeSalle;
    }
    public List<Film> getFilmsBySalle(Salle salle){
        List<Film> listeFilm = new ArrayList<Film>();
        HibernateUtils hibernate = new HibernateUtils();
        SessionFactory sessionFactory = hibernate.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();


        Criteria criteria = session.createCriteria(FilmSalle.class);
        criteria.add(Restrictions.eq("idSalle", salle.getId()));
        criteria.setProjection(Projections.property("idFilm"));
        List result = criteria.list();
        Iterator iter = result.iterator();
        while (iter.hasNext()) {
            Integer id = (Integer) iter.next();
            System.out.println("salle : " + result.get(1));
            String queryStr = "select f from Film f where "
                    + "f.id =:salleFilmId";
            Query query = session.createQuery(queryStr);
            query.setParameter("salleFilmId", id);
            for (Film film : (List<Film>) (query.list())) {
                listeFilm.add(film);
            }
        }

        return listeFilm;
    }

    public void deleteElement(Salle salle,Film film){
        HibernateUtils hibernate = new HibernateUtils();
        SessionFactory sessionFactory = hibernate.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        GestionnaireSalle gestSalle = new GestionnaireSalle(session);
        GestionnaireFilm gestFilm = new GestionnaireFilm(session);
        List<FilmSalle> filmsalle = (List<FilmSalle>) session.createCriteria(FilmSalle.class)
                                .add(Restrictions.eq("idFilm",film.getId())).list();
        for(FilmSalle filmsa : filmsalle) {
            session.delete(filmsa);
        }
        filmsalle = (List<FilmSalle>) session.createCriteria(FilmSalle.class)
                .add(Restrictions.eq("idSalle",salle.getId())).list();
        for(FilmSalle filmsa : filmsalle) {
            session.delete(filmsa);
        }
        gestFilm.deleteFilm(film);
        gestSalle.deleteSalle(salle);
        session.getTransaction().commit();
        session.close();
    }

}
