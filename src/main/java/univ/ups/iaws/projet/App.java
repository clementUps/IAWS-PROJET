package univ.ups.iaws.projet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import univ.ups.iaws.Beans.Film;
import univ.ups.iaws.Beans.Salle;
import univ.ups.iaws.Hibernate.HibernateUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        System.out.println( "Hello World!" );

      /*  HibernateUtils hibernate = new HibernateUtils();
        SessionFactory sessionFactory = hibernate.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();*/
        List<Film> films = new ArrayList<Film>();

        Film film = new Film();
        film.setAnnee(10);
        film.setImdbId("azertyu");
        film.setTitre("film1");
        films.add(film);

        film = new Film();
        film.setAnnee(10);
        film.setImdbId("qsdfgh");
        film.setTitre("film2");
        films.add(film);

        film = new Film();
        film.setAnnee(10);
        film.setImdbId("wxcvbn");
        film.setTitre("film3");
        films.add(film);

        film = new Film();
        film.setAnnee(10);
        film.setImdbId("pmlk");
        film.setTitre("film4");
        films.add(film);


        film = new Film();
        film.setAnnee(10);
        film.setImdbId("654fg");
        film.setTitre("film5");
        films.add(film);


        Salle salle = new Salle();
        salle.setNbSalle(5);
        salle.setVille("Toulouse");

        GestionnaireLiaison gestionnaire = new GestionnaireLiaison();
        gestionnaire.saveAll(salle,films);


        Salle salle2 = new Salle();
        salle2.setNbSalle(2);
        salle2.setVille("Paris");
        gestionnaire.saveAll(salle2,films);

        List<Salle> list = gestionnaire.getSallesByFilm(film);
        for(Salle sallels: list){
            System.out.println(" id "+sallels.getId()+" ville "+sallels.getVille());
        }

        List<Film> listFilm = gestionnaire.getFilmsBySalle(salle);
        for(Film filmS: listFilm){
            System.out.println(" id "+filmS.getId()+" ville "+filmS.getTitre());
        }
        gestionnaire.deleteElement(salle,film);


        /*session.flush();
        session.getTransaction().commit();
        session.close();
        hibernate.shutdown();
    */
    }
}
