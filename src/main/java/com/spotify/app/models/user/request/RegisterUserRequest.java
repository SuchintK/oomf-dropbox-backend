package com.spotify.app.models.user.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterUserRequest {
    @JsonProperty("access_token")
    public String getAccess_token() {
        return this.access_token; }
    public void setAccess_token(String access_token) {
        this.access_token = access_token; }
    String access_token;
    @JsonProperty("token_type")
    public String getToken_type() {
        return this.token_type; }
    public void setToken_type(String token_type) {
        this.token_type = token_type; }
    String token_type;
    @JsonProperty("scope")
    public String getScope() {
        return this.scope; }
    public void setScope(String scope) {
        this.scope = scope; }
    String scope;
    @JsonProperty("expires_in")
    public int getExpires_in() {
        return this.expires_in; }
    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in; }
    int expires_in;
    @JsonProperty("refresh_token")
    public String getRefresh_token() {
        return this.refresh_token; }
    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token; }
    String refresh_token;
    @JsonProperty("client_id")
    public String getClient_id(){
        return this.client_id;
    }
    public void setClient_id(String client_id){
        this.client_id = client_id;
    }
    String client_id;
}
