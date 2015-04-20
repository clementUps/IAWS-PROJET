package univ.ups.iaws.Beans;

/**
 * Created by 20901927 on 25/03/2015.
 */
public class Film {

    private int id;
    private String imdbId;
    private String titre;
    private int annee;


    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public Film (String titre, int annee, String imdbId) {
        this.titre = titre;
        this.annee = annee;
        this.imdbId = imdbId;
    }
    public Film(){

    }
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

}
