package com.spotify.app.models.searchitems.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Album{
    @JsonProperty("album_type")
    public String getAlbum_type() {
        return this.album_type; }
    public void setAlbum_type(String album_type) {
        this.album_type = album_type; }
    String album_type;
    @JsonProperty("artists")
    public ArrayList<Artist> getArtists() {
        return this.artists; }
    public void setArtists(ArrayList<Artist> artists) {
        this.artists = artists; }
    ArrayList<Artist> artists;
    @JsonProperty("available_markets")
    public ArrayList<String> getAvailable_markets() {
        return this.available_markets; }
    public void setAvailable_markets(ArrayList<String> available_markets) {
        this.available_markets = available_markets; }
    ArrayList<String> available_markets;
    @JsonProperty("external_urls")
    public ExternalUrls getExternal_urls() {
        return this.external_urls; }
    public void setExternal_urls(ExternalUrls external_urls) {
        this.external_urls = external_urls; }
    ExternalUrls external_urls;
    @JsonProperty("href")
    public String getHref() {
        return this.href; }
    public void setHref(String href) {
        this.href = href; }
    String href;
    @JsonProperty("id")
    public String getId() {
        return this.id; }
    public void setId(String id) {
        this.id = id; }
    String id;
    @JsonProperty("images")
    public ArrayList<Image> getImages() {
        return this.images; }
    public void setImages(ArrayList<Image> images) {
        this.images = images; }
    ArrayList<Image> images;
    @JsonProperty("is_playable")
    public boolean getIs_playable() {
        return this.is_playable; }
    public void setIs_playable(boolean is_playable) {
        this.is_playable = is_playable; }
    boolean is_playable;
    @JsonProperty("name")
    public String getName() {
        return this.name; }
    public void setName(String name) {
        this.name = name; }
    String name;
    @JsonProperty("release_date")
    public String getRelease_date() {
        return this.release_date; }
    public void setRelease_date(String release_date) {
        this.release_date = release_date; }
    String release_date;
    @JsonProperty("release_date_precision")
    public String getRelease_date_precision() {
        return this.release_date_precision; }
    public void setRelease_date_precision(String release_date_precision) {
        this.release_date_precision = release_date_precision; }
    String release_date_precision;
    @JsonProperty("total_tracks")
    public int getTotal_tracks() {
        return this.total_tracks; }
    public void setTotal_tracks(int total_tracks) {
        this.total_tracks = total_tracks; }
    int total_tracks;
    @JsonProperty("type")
    public String getType() {
        return this.type; }
    public void setType(String type) {
        this.type = type; }
    String type;
    @JsonProperty("uri")
    public String getUri() {
        return this.uri; }
    public void setUri(String uri) {
        this.uri = uri; }
    String uri;
}
