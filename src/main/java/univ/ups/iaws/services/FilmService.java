package univ.ups.iaws.services;

import univ.ups.iaws.Beans.Film;

import java.util.List;

/**
 * Created by Max on 01/04/2015.
 */
public interface FilmService {
    /**
     * Retourne le Film correspondant a un id un titre et une année.
     * @param imdbID
     *@param titre
     *@param annee
     * @return
     */

    public Film findAllFilms(String imdbID, String titre ,int annee);
}

