package services;

import univ.ups.iaws.Beans.Film;
import univ.ups.iaws.projet.GestionnaireLiaison;

/**
 * Created by Max on 17/04/2015.
 */
public class FilmServiceImpl implements FilmService{
	
    public Film findAllFilms(String imdbID, String titre, int annee) {
        return new Film("",1,"");
    }

}
