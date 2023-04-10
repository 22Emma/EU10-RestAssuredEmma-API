package com.cydeo.pojos;
import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(value = "id",allowSetters = true)// I don't want you to send "id" value while serialization

public class Spartan {

    private int id;
    private String name;
    private String gender;
    private  long phone;


}
