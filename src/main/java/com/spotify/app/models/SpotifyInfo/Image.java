package com.spotify.app.models.SpotifyInfo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Image {
    @JsonProperty("url")
    public String getUrl() {
        return this.url; }
    public void setUrl(String url) {
        this.url = url; }
    String url;
    @JsonProperty("height")
    public int getHeight() {
        return this.height; }
    public void setHeight(int height) {
        this.height = height; }
    int height;
    @JsonProperty("width")
    public int getWidth() {
        return this.width; }
    public void setWidth(int width) {
        this.width = width; }
    int width;
}
