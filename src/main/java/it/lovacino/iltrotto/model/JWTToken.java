package it.lovacino.iltrotto.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JWTToken {
    @JsonProperty("id_token")
    private String idToken;

    public JWTToken(){
    }

    public JWTToken(String idToken) {
        this.idToken = idToken;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }
}
