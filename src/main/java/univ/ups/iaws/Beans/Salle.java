package univ.ups.iaws.Beans;

/**
 * Created by 20901927 on 25/03/2015.
 */
public class Salle {
    private int id;
    private int nbSalle;
    private String ville;

    public Salle() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbSalle() {
        return nbSalle;
    }

    public void setNbSalle(int nbSalle) {
        this.nbSalle = nbSalle;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}
