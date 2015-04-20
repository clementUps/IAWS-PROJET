package services;


import java.util.List;

import univ.ups.iaws.Beans.Salle;

/**
 * @author franck Silvestre
 */
public interface ReleveNoteService {

    /**
     * Retourne tous les évaluation correspondant à une année scolaire, un niveau
     * et un semestre
     * @param anneeScolaire  l'année scolaire
     * @param niveau  le niveau
     * @param semestre le semestre
     * @return les évaluations correspondant aux critères fournis
     */
    public List<Salle> findAllEvaluationsForAnneeScolaireNiveauAndSemestre(
            int id,
            String niveau,
            String semestre
    );
}
