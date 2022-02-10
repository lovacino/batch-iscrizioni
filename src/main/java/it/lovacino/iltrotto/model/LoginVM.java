package it.lovacino.iltrotto.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginVM {
    @JsonProperty
    private String username;
    @JsonProperty
    private String password;
    @JsonProperty
    private boolean rememberMe;

    public LoginVM(){
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

}
