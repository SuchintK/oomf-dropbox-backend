package com.spotify.app.models.searchitems.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class SearchItemResponse {
    @JsonProperty("name")
    public String getName() {
        return this.name; }
    public void setName(String name) {
        this.name = name; }
    String name;
    @JsonProperty("artists")
    public ArrayList<String> getArtists() {
        return this.artists; }
    public void setArtists(ArrayList<String> artists) {
        this.artists = artists; }
    ArrayList<String> artists;
    @JsonProperty("image")
    public String getImage() {
        return this.image; }
    public void setImage(String image) {
        this.image = image; }
    String image;
    @JsonProperty("uri")
    public String getUri() {
        return this.uri; }
    public void setUri(String uri) {
        this.uri = uri; }
    String uri;
}
