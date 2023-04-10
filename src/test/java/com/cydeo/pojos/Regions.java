package com.cydeo.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter // from lombok
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true) // this is from jackson dependency
public class Regions {

    private List<Region> items;
    private int count;

}
