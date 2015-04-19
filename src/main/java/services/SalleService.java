package services;

import univ.ups.iaws.Beans.Film;
import univ.ups.iaws.Beans.Salle;

import java.util.List;

/**
 * Created by Max on 01/04/2015.
 */
public interface SalleService {
    /**
     * Retourne le Film correspondant a un id un titre et une année.
     * @param imdbID
     *@param titre
     *@param annee
     * @return
     */

    public List<Salle> findAllSalle(String imdbID, String titre, int annee);
}

