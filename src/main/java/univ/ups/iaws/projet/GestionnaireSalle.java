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
public class GestionnaireSalle {
    private Salle salle;
    private Session session;
    private HibernateUtils hibernate;
    public GestionnaireSalle(){
        HibernateUtils hibernate = new HibernateUtils();
        SessionFactory sessionFactory = hibernate.getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
    }
    public GestionnaireSalle(Session session){
        this.session = session;
    }
    public Salle saveSalle(Salle newSalle){
        session.saveOrUpdate(newSalle);
        return newSalle;
    }
    public List<Salle> saveSalle(List<Salle> newFilm){
        List<Salle> listeSalle = new ArrayList<Salle>();
        Salle salleTempo;
        for(Salle eachSalle:newFilm){
            salleTempo = saveSalle(eachSalle);
            listeSalle.add(salleTempo);
        }
        return listeSalle;
    }

    public void deleteSalle(Salle salle){
        session.delete(salle);
    }

    public void shutDownFactory(){
        session.close();
        hibernate.shutdown();
    }




}
