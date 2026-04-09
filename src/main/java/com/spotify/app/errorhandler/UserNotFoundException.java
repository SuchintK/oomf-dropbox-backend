package com.spotify.app.errorhandler;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String id){
        super(id);
    }
}
