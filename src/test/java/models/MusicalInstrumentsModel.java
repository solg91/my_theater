package models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by solg on 18.12.2016.
 */
public class MusicalInstrumentsModel extends Model {
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
