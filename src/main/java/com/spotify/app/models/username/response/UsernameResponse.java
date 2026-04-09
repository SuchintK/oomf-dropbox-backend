package com.spotify.app.models.username.response;

public class UsernameResponse {
    public String username;
    public UsernameResponse(String username){
        this.username = username;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
