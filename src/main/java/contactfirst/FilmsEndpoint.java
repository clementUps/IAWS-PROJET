package contactfirst;


import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.Namespace;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.server.endpoint.annotation.XPathParam;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import services.SalleService;
import univ.ups.iaws.Beans.Film;
import univ.ups.iaws.Beans.Salle;



@Endpoint
public class FilmsEndpoint {
    private Film films;
    private SalleService salleService;


    private static final String NAMESPACE_URI = "http://iaws/ws/contractfirst/exemple";

    public FilmsEndpoint(SalleService salleService){
        this.salleService = salleService;

    }
    @PayloadRoot(localPart ="FilmRequest", namespace = NAMESPACE_URI)
    @ResponsePayload
    @Namespace(prefix = "s", uri=NAMESPACE_URI)
    public Element handleFilmsRequest(@XPathParam("/s:FilmRequest/@imdbID")String imdbID) throws Exception {


        // invoque le service "FilmService" pour récupérer les objets recherchés
        List<Salle> salle = salleService.findAllSalle(imdbID);
        // Transforme en élément XML ad-hoc pour le retour
        createDoc(salle);
        System.out.println("bonour ca va ?");
        Element elt = XmlHelper.getRootElementFromFileInClasspath("/salle.xml") ;
        System.out.println("bien et toi ? ");
        return  elt;

    }

    public static void createDoc(List<Salle> salles){
        Element idElement;
        Element salleElement;
        Element villeElement;
        Element nbSalleElement;
        try{
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Salles");
            doc.appendChild(rootElement);
            for(Salle salle:salles) {
                // salle elements
                salleElement = doc.createElement("salle");
                rootElement.appendChild(salleElement);

                // nbSalle elements
                nbSalleElement = doc.createElement("nbSalle");
                nbSalleElement.appendChild(doc.createTextNode(""+salle.getNbSalle()));
                salleElement.appendChild(nbSalleElement);

                // ville elements
                villeElement = doc.createElement("ville");
                villeElement.appendChild(doc.createTextNode(""+salle.getVille()));
                salleElement.appendChild(villeElement);
            }
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("src/main/resources/salle.xml"));
            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

}
