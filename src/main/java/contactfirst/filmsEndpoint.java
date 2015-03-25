package contactfirst;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.Namespace;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.server.endpoint.annotation.XPathParam;
import org.w3c.dom.Element;

import java.util.List;

/**
 * @author franck Silvestre
 */
@Endpoint
public class FilmsEndpoint {
    private Films films;

    private static final String NAMESPACE_URI = "http://www.omdbapi.com/?s=the+matrix&y=&plot=short&r=json";

    
    public FilmsEndpoint(Films films) {
        this.films = films;
    }

    @PayloadRoot(localPart = "FilmsRequest", namespace = NAMESPACE_URI)
    @ResponsePayload
    @Namespace(prefix = "s", uri=NAMESPACE_URI)
    public Element handleFilmsRequest(@XPathParam("/s:FilmsRequest/s:title")String title,
    		@XPathParam("/s:FilmsRequest/s:imdbID")String imdbID,
    		@XPathParam("s:FilmsRequest/s:year")String year,
            @XPathParam("s:FilmsRequest/s:type")String type) throws Exception {

        // parse le XML de FilmsRequest pour extraire les informations de
        // title, du imdbID et du semestre  et creer les objets ad-hoc
        Title title = new Title(title);
        ID imdbID = new ID(imdbID);
        Year year = new Year(year);
        Type type = new Type(type);


        // invoque le service "releveNoteService" pour récupérer les objets recherchés
        //
        List<Evaluation> evals = releveNotesService.findAllEvaluationsForAnneeScolaireNiveauAndSemestre(anneeScolaire, niveau, semestre);

        // Transforme en élément XML ad-hoc pour le retour
        // Ici, on prend le parti de renvoyer un fichier XML statique. Il faudrait traiter la
        // liste des évaluations avec une API XML pour fournir l'élément réponse de manière
        // dynamique

        Element elt = XmlHelper.getRootElementFromFileInClasspath("ReleveNotes.xml") ;
        return  elt;

    }

}
