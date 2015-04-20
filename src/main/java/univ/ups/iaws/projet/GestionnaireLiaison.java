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
import Exception.requete.NullRequeteException;
import java.util.Objects;

/**
 * Created by clement on 06/04/2015.
 */
public class GestionnaireLiaison {
    private Salle salle;
    private List<Film> filmListe = new ArrayList<Film>();
    private Session session;
    public static Session createSession(){
        HibernateUtils hibernate = new HibernateUtils();
        SessionFactory sessionFactory = hibernate.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        return session;
    }
    public GestionnaireLiaison(Session session){
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    public FilmSalle saveAll(Salle newSalle,List<Film> films){
        FilmSalle filmSalle = null;
        Film filmTempo;
        Salle salleTempo;
        GestionnaireSalle gestSalle = new GestionnaireSalle(session);
        GestionnaireFilm gestFilm = new GestionnaireFilm(session);
        salle = gestSalle.saveSalle(newSalle);

        for(Film film : films){
            filmTempo = gestFilm.saveFilm(film);
            filmListe.add(filmTempo);
            filmSalle = new FilmSalle();
            filmSalle.setIdFilm(filmTempo.getId());
            filmSalle.setIdSalle(salle.getId());
            session.saveOrUpdate(filmSalle);
        }
        session.flush();
        session.getTransaction().commit();
        return filmSalle;
    }
    private Iterator searchFilm(Film film)throws NullRequeteException{
        if(film.getId()== 0) {
            try{
                String queryStr = "select f from Film as f where f.imdbId=:filmIMdbId";
                Query query = session.createQuery(queryStr);
                query.setParameter("filmIMdbId", film.getImdbId());
                Film f =  (Film)query.uniqueResult();
                film = f;
            }catch(Exception e){
                e.printStackTrace();
                throw new NullRequeteException();
            }
        }
        Criteria criteria = session.createCriteria(FilmSalle.class);
        criteria.add(Restrictions.eq("idFilm",film.getId()));
        criteria.setProjection(Projections.property("idSalle"));
        List result = criteria.list();
        Iterator iter = result.iterator();
        return iter;
    }
    public List<Salle> getSallesByCritere(Film film)throws NullRequeteException{
        List<Salle> listeSalle = new ArrayList<Salle>();
        Iterator iter = searchFilm(film);
        while (iter.hasNext()) {
            Integer id = (Integer) iter.next();
            listeSalle.addAll(createQuerySalle(id, null, null, session));
        }
        return listeSalle;
    }
    public List<Salle> getSallesByCritere(int nbSalle){
        return createQuerySalle(null,null,nbSalle,session);
    }

    public List<Salle> getSallesByCritere(String ville){
        return createQuerySalle(null,ville,null,session);
    }
    public List<Salle> getSallesByCritere(int nbSalle,String ville){
        return createQuerySalle(null,ville,nbSalle,session);
    }
    public List<Salle> getSallesByCritere(Film film,int nbSalle)throws NullRequeteException{
        List<Salle> listeSalle = new ArrayList<Salle>();
        Iterator iter = searchFilm(film);
        while (iter.hasNext()) {
            Integer id = (Integer) iter.next();
            listeSalle.addAll(createQuerySalle(id, null, nbSalle, session));
        }
        return listeSalle;
    }
    public List<Salle> getSallesByCritere(Film film,String ville)throws NullRequeteException{
        List<Salle> listeSalle = new ArrayList<Salle>();
        Iterator iter = searchFilm(film);
        while (iter.hasNext()) {
            Integer id = (Integer) iter.next();
            listeSalle.addAll(createQuerySalle(id, ville, null, session));
        }
        return listeSalle;
    }

    public List<Salle> getSallesByCritere(Film film,int nbSalle,String ville)throws NullRequeteException{
        List<Salle> listeSalle = new ArrayList<Salle>();
        Iterator iter = searchFilm(film);
        while (iter.hasNext()) {
            Integer id = (Integer) iter.next();
            listeSalle.addAll(createQuerySalle(id, ville, nbSalle, session));
        }
        return listeSalle;
    }

    private List<Salle> createQuerySalle(Integer id,String ville,Integer nbSalle,Session session){
        List<Salle> listeSalle = new ArrayList<Salle>();
        String queryStr = "select s from Salle as s where ";
        boolean isNew= true;
        if(id != null){
            queryStr += "s.id =:salleFilmId";
            isNew = false;
        }
        if(nbSalle!= null){
            if(!isNew){
                queryStr += " and ";
            }
            queryStr += "s.nbSalle =:salleNb";
        }
        if(ville != null){
            if(!isNew){
                queryStr += " and ";
            }
            queryStr += "s.ville =:salleVille";
        }
        System.out.println("query "+queryStr);
        Query query = session.createQuery(queryStr);
        if(id!=null){
            query.setParameter("salleFilmId", id);
        }
        if(ville!=null){
            query.setParameter("salleVille", ville);
        }
        if(nbSalle != null){
            query.setParameter("salleNb", nbSalle);
        }
        for (Salle salle : (List<Salle>) (query.list())) {
            listeSalle.add(salle);
        }
        return listeSalle;
    }

    public List<Film> getFilmsBySalle(Salle salle){
        List<Film> listeFilm = new ArrayList<Film>();

        Criteria criteria = session.createCriteria(FilmSalle.class);
        criteria.add(Restrictions.eq("idSalle", salle.getId()));

        criteria.setProjection(Projections.property("idFilm"));
        List result = criteria.list();
        Iterator iter = result.iterator();
        while (iter.hasNext()) {
            Integer id = (Integer) iter.next();
            System.out.println("salle : " + result.get(0));
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
    }

}
