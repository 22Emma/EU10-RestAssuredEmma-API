package com.cydeo.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString

public class Region {
    //if your jsonKey and variable name not matching, you can map it with jsonProperty
    @JsonProperty("region_id") // normally the key is region_id in json, but i am telling that i will use regionId
    private int regionId;
    private String region_name;
    private List<Link> links;


}

