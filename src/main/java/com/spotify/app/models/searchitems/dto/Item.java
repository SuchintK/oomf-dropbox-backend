package com.spotify.app.models.searchitems.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Item{
    @JsonProperty("album")
    public Album getAlbum() {
        return this.album; }
    public void setAlbum(Album album) {
        this.album = album; }
    Album album;
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
    @JsonProperty("disc_number")
    public int getDisc_number() {
        return this.disc_number; }
    public void setDisc_number(int disc_number) {
        this.disc_number = disc_number; }
    int disc_number;
    @JsonProperty("duration_ms")
    public int getDuration_ms() {
        return this.duration_ms; }
    public void setDuration_ms(int duration_ms) {
        this.duration_ms = duration_ms; }
    int duration_ms;
    @JsonProperty("explicit")
    public boolean getExplicit() {
        return this.explicit; }
    public void setExplicit(boolean explicit) {
        this.explicit = explicit; }
    boolean explicit;
    @JsonProperty("external_ids")
    public ExternalIds getExternal_ids() {
        return this.external_ids; }
    public void setExternal_ids(ExternalIds external_ids) {
        this.external_ids = external_ids; }
    ExternalIds external_ids;
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
    @JsonProperty("is_local")
    public boolean getIs_local() {
        return this.is_local; }
    public void setIs_local(boolean is_local) {
        this.is_local = is_local; }
    boolean is_local;
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
    @JsonProperty("popularity")
    public int getPopularity() {
        return this.popularity; }
    public void setPopularity(int popularity) {
        this.popularity = popularity; }
    int popularity;
    @JsonProperty("preview_url")
    public Object getPreview_url() {
        return this.preview_url; }
    public void setPreview_url(Object preview_url) {
        this.preview_url = preview_url; }
    Object preview_url;
    @JsonProperty("track_number")
    public int getTrack_number() {
        return this.track_number; }
    public void setTrack_number(int track_number) {
        this.track_number = track_number; }
    int track_number;
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