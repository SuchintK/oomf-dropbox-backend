package com.spotify.app.models.SongPackage;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SongRequest {
    @JsonProperty("song_uri")
    public String getSong_uri() {
        return this.song_uri; }
    public void setSong_uri(String song_uri) {
        this.song_uri = song_uri; }
    String song_uri;
}
