package RestFull;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.DataInputStream;
import java.io.File;
import java.io.InputStream;


/**
 * Created by clement on 18/04/2015.
 */
public class ClientFilm {
    private static final String API_URI = "http://www.omdbapi.com/?";
    private static final String TITRE_URI = "s=";
    private static final String LIST_TITRE_URI = "t=";
    private static final String ANNEE_URI = "&y=";
    private static final String END_URI = "&plot=short&r=xml";


    public static InputStream getEvals(String titre,String annee,boolean isTitre){
        final Client c = ClientBuilder.newClient();
    	String build =  API_URI+(isTitre?TITRE_URI:LIST_TITRE_URI)+titre+ANNEE_URI+annee+END_URI;
        final WebTarget wt = c.target(build);
        try {
        	System.out.println(wt.getUri());
            Response response = wt.request(MediaType.TEXT_XML).get();
            InputStream in = response.readEntity(InputStream.class);
            return in;
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
