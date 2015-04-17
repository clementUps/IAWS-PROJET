package univ.ups.iaws.services;

import univ.ups.iaws.Beans.Film;

/**
 * Created by Max on 17/04/2015.
 */
public class FilmServiceImpl implements FilmService{
    @Override
    public Film findAllFilms(String imdbID, String titre, int annee) {
        return new Film();
    }

}
