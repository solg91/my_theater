package com.example.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by solg on 18.12.2016.
 */
public class ActorsModel extends Model {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("musicalInstruments")
    private List<Integer> musicalInstruments = null;
    @JsonProperty("currentPlay")
    private Integer currentPlay;
    @JsonProperty("previousPlay")
    private List<Integer> previousPlay = null;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("size")
    private Integer size;
    @JsonProperty("education")
    private EducationModel education;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("musicalInstruments")
    public List<Integer> getMusicalInstruments() {
        return musicalInstruments;
    }

    @JsonProperty("musicalInstruments")
    public void setMusicalInstruments(List<Integer> musicalInstruments) {
        this.musicalInstruments = musicalInstruments;
    }

    @JsonProperty("currentPlay")
    public Integer getCurrentPlay() {
        return currentPlay;
    }

    @JsonProperty("currentPlay")
    public void setCurrentPlay(Integer currentPlay) {
        this.currentPlay = currentPlay;
    }

    @JsonProperty("previousPlay")
    public List<Integer> getPreviousPlay() {
        return previousPlay;
    }

    @JsonProperty("previousPlay")
    public void setPreviousPlay(List<Integer> previousPlay) {
        this.previousPlay = previousPlay;
    }

    @JsonProperty("phone")
    public String getPhone() {
        return phone;
    }

    @JsonProperty("phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonProperty("size")
    public Integer getSize() {
        return size;
    }

    @JsonProperty("size")
    public void setSize(Integer size) {
        this.size = size;
    }

    @JsonProperty("education")
    public EducationModel getEducation() {
        return education;
    }

    @JsonProperty("education")
    public void setEducation(EducationModel education) {
        this.education = education;
    }


}
