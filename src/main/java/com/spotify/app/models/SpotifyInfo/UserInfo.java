package com.spotify.app.models.SpotifyInfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spotify.app.models.searchitems.dto.ExternalUrls;

import java.util.List;

public class UserInfo {
    @JsonProperty("country")
    public String getCountry() {
        return this.country; }
    public void setCountry(String country) {
        this.country = country; }
    String country;
    @JsonProperty("display_name")
    public String getDisplay_name() {
        return this.display_name; }
    public void setDisplay_name(String display_name) {
        this.display_name = display_name; }
    String display_name;
    @JsonProperty("email")
    public String getEmail() {
        return this.email; }
    public void setEmail(String email) {
        this.email = email; }
    String email;
    @JsonProperty("explicit_content")
    public ExplicitContent getExplicit_content() {
        return this.explicit_content; }
    public void setExplicit_content(ExplicitContent explicit_content) {
        this.explicit_content = explicit_content; }
    ExplicitContent explicit_content;
    @JsonProperty("external_urls")
    public ExternalUrls getExternal_urls() {
        return this.external_urls; }
    public void setExternal_urls(ExternalUrls external_urls) {
        this.external_urls = external_urls; }
    ExternalUrls external_urls;
    @JsonProperty("followers")
    public Followers getFollowers() {
        return this.followers; }
    public void setFollowers(Followers followers) {
        this.followers = followers; }
    Followers followers;
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
    public List<Image> getImages() {
        return this.images; }
    public void setImages(List<Image> images) {
        this.images = images; }
    List<Image> images;
    @JsonProperty("product")
    public String getProduct() {
        return this.product; }
    public void setProduct(String product) {
        this.product = product; }
    String product;
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
