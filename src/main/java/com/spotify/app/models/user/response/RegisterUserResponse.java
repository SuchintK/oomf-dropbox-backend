package com.spotify.app.models.user.response;

public class RegisterUserResponse {

    public RegisterUserResponse(){

    }

    public boolean isUserRegistered;

    public String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean getIsUserRegistered() {
        return this.isUserRegistered; }
    public void setIsUserRegistered(boolean isUserRegistered) {
        this.isUserRegistered = isUserRegistered; }
}
