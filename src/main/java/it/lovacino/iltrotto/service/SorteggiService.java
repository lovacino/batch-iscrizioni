package it.lovacino.iltrotto.service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.specimpl.MultivaluedMapImpl;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URLEncoder;

public class SorteggiService {

    private static final Logger logger = Logger.getRootLogger();
    private ObjectMapper mapper = new ObjectMapper(new JsonFactory()).registerModule(new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    private static SorteggiService _instance;

    private SorteggiService(){
        logger.setLevel(Level.INFO);
    }

    public static SorteggiService getInstance() {
        if( _instance == null ){
           synchronized (SorteggiService.class) {
               if (_instance == null) {
                    _instance = new SorteggiService();
               }
           }
        }
        return _instance;
    }

    public int batchSorteggi(String dataCorsa) throws Exception{
        logger.info("[batchSorteggi] - [START] --- [INPUT]: dataCorsa: " + dataCorsa  );
        dataCorsa = URLEncoder.encode(dataCorsa, "UTF-8");
        String token = AuthService.getInstance().login();
        ResteasyClient client = new ResteasyClientBuilder().build();
        MultivaluedMap<String, Object> query = new MultivaluedMapImpl<>();
        query.putSingle("dataCorsa", dataCorsa);
        ResteasyWebTarget target = client.target(UriBuilder.fromPath("http://127.0.0.1:8080/api/iscrizioniCorses/getListaMailCorseCavalli")).queryParams(query);
        Response response = target.request().header("Authorization", "Bearer " + token).header("X-Request-Id", java.util.UUID.randomUUID().toString()).buildGet().invoke();
        logger.info("[batchSorteggi] - [END OK] --- [RETURN]: " + response.getStatus());
        return response.getStatus();
    }


}
