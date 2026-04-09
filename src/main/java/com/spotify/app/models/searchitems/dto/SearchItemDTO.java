package com.spotify.app.models.searchitems.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchItemDTO {
    @JsonProperty("tracks")
    public Tracks getTracks() {
        return this.tracks; }
    public void setTracks(Tracks tracks) {
        this.tracks = tracks; }
    Tracks tracks;
}
