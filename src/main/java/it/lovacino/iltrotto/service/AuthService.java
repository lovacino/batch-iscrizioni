package it.lovacino.iltrotto.service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.lovacino.iltrotto.model.JWTToken;
import it.lovacino.iltrotto.model.LoginVM;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.time.Instant;

public class AuthService {

    private static AuthService _instance;
    private static JWTToken authorizationToken;
    private static Instant dateLastRefreshToken;
    private ObjectMapper mapper = new ObjectMapper(new JsonFactory());

    private AuthService(){

    }

    public static AuthService getInstance() {
        if( _instance == null ){
           synchronized (AuthService.class) {
               if (_instance == null) {
                    _instance = new AuthService();
               }
           }
        }
        return _instance;
    }

    public String login() throws Exception{
        if( authorizationToken != null &&
                dateLastRefreshToken.toEpochMilli() + 300000 /*add 5 min in millis*/  > Instant.now().toEpochMilli() ) {
            return authorizationToken.getIdToken();
        }

        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(UriBuilder.fromPath("http://localhost:8080/api/authenticate"));

        LoginVM loginVM = new LoginVM();
        loginVM.setUsername("admin");
        loginVM.setPassword("admin44df22@1");
        Response response = target.request().post( Entity.entity(loginVM, "application/json" ) );
        String json = response.readEntity(String.class);
        authorizationToken = mapper.readValue(json, JWTToken.class);
        dateLastRefreshToken = Instant.now();
        return authorizationToken.getIdToken();
    }

}
