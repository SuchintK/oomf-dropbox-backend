package com.spotify.app.models.searchitems.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExternalIds{
    @JsonProperty("isrc")
    public String getIsrc() {
        return this.isrc; }
    public void setIsrc(String isrc) {
        this.isrc = isrc; }
    String isrc;
}