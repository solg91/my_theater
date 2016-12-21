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
public class EducationModel {
    @JsonProperty("name")
    private String name;
    @JsonProperty("faculty")
    private String faculty;
    @JsonProperty("yearOfEnding")
    private String yearOfEnding;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("faculty")
    public String getFaculty() {
        return faculty;
    }

    @JsonProperty("faculty")
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    @JsonProperty("yearOfEnding")
    public String getYearOfEnding() {
        return yearOfEnding;
    }

    @JsonProperty("yearOfEnding")
    public void setYearOfEnding(String yearOfEnding) {
        this.yearOfEnding = yearOfEnding;
    }

}
