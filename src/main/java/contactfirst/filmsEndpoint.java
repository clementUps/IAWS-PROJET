package contactfirst;


import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.*;
import org.w3c.dom.Element;
import univ.ups.iaws.Beans.Film;
import univ.ups.iaws.services.FilmService;
import univ.ups.iaws.services.FilmServiceImpl;


/**
 * @author franck Silvestre
 */
@Endpoint
public class filmsEndpoint {
    private Film films;
    private FilmServiceImpl fs;


    private static final String NAMESPACE_URI = "http://www.omdbapi.com/?s=the+matrix&y=&plot=short&r=json";

    
    public filmsEndpoint(Film films) {
        this.films = films;
    }

    @PayloadRoot(localPart = "FilmsRequest", namespace = NAMESPACE_URI)
    @ResponsePayload
    @Namespace(prefix = "s", uri=NAMESPACE_URI)
    public Element handleFilmsRequest(@XPathParam("/s:FilmsRequest/s:titre")String titre,
    		@XPathParam("/s:FilmsRequest/s:imdbID")String imdbID,
    		@XPathParam("s:FilmsRequest/s:annee") int annee) throws Exception {

        // parse le XML de FilmsRequest pour extraire les informations de
        // title, du imdbID et creer les objets ad-hoc
        titre = new String(titre);
        imdbID = new String(imdbID);


        // invoque le service "FilmService" pour récupérer les objets recherchés
        //
        Film evals = fs.findAllFilms(imdbID,titre,annee);

        // Transforme en élément XML ad-hoc pour le retour
        // Ici, on prend le parti de renvoyer un fichier XML statique. Il faudrait traiter la
        // liste des évaluations avec une API XML pour fournir l'élément réponse de manière
        // dynamique

        Element elt = XmlHelper.getRootElementFromFileInClasspath("Film.xml") ;
        return  elt;

    }

}
