package com.spotify.app.models.searchitems.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Artist{
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
    @JsonProperty("name")
    public String getName() {
        return this.name; }
    public void setName(String name) {
        this.name = name; }
    String name;
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
