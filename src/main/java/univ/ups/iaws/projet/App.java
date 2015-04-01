package univ.ups.iaws.projet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import univ.ups.iaws.Beans.Film;
import univ.ups.iaws.Hibernate.HibernateUtils;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        System.out.println( "Hello World!" );

        HibernateUtils hibernate = new HibernateUtils();
        SessionFactory sessionFactory = hibernate.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Film film = new Film();
        film.setAnnee(10);
        film.setImdbId("aze");
        film.setTitre("titre");
        session.save(film);
        session.getTransaction().commit();
        session.close();


    }
}
