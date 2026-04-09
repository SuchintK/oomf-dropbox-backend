package com.spotify.app.models.SpotifyInfo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExplicitContent {
    @JsonProperty("filter_enabled")
    public boolean getFilter_enabled() {
        return this.filter_enabled; }
    public void setFilter_enabled(boolean filter_enabled) {
        this.filter_enabled = filter_enabled; }
    boolean filter_enabled;
    @JsonProperty("filter_locked")
    public boolean getFilter_locked() {
        return this.filter_locked; }
    public void setFilter_locked(boolean filter_locked) {
        this.filter_locked = filter_locked; }
    boolean filter_locked;
}
