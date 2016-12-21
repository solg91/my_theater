package com.example.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by solg on 18.12.2016.
 */
public class MusicalInstrumentsModel {
    @JsonProperty("id")
    public Integer id;
    @JsonProperty("playID")
    private Integer playID;
    @JsonProperty("name")
    public String name;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("playID")
    public Integer getPlayID() {
        return playID;
    }

    @JsonProperty("playID")
    public void setPlayID(Integer playID) {
        this.playID = playID;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

}
