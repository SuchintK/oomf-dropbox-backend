package com.spotify.app.models.LoginUserPackage;

public class LoginUser {
    public String userId;

    public LoginUser(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
