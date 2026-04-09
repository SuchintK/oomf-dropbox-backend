package com.spotify.app.models.UserDB;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "users_table")
public class SpotifyDBUser{
    @Id
    @Column(name = "user_id")
    public String userId;

    @Column(name = "refresh_token")
    public String refreshToken;

    @Column(name = "username")
    public String username;

    @Column(name = "access_token")
    public String accessToken;

    @Column(name = "playlist_id")
    public String playlistId;

    @Column(name = "expires_in")
    public Integer expiresIn;

    @Column(name = "last_refresh_time")
    public Long lastRefreshTime;

    @Column(name = "client_id")
    public String clientId;

    public SpotifyDBUser(){
        System.out.println("Default constructor is being called!");
    }

    public SpotifyDBUser(String userId, String refreshToken, String accessToken, String playlistId, Integer expiresIn, String clientId) {
        this.userId = userId;
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
        this.playlistId = playlistId;
        this.expiresIn = expiresIn;
        this.lastRefreshTime = Instant.now().getEpochSecond();
        this.clientId = clientId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Long getLastRefreshTime() {
        return lastRefreshTime;
    }

    public void setLastRefreshTime(Long lastRefreshTime) {
        this.lastRefreshTime = lastRefreshTime;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "SpotifyDBUser{" +
                "userId='" + userId + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", username='" + username + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", playlistId='" + playlistId + '\'' +
                ", expiresIn=" + expiresIn +
                ", lastRefreshTime=" + lastRefreshTime +
                '}';
    }
}
