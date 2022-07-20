package com.cybertek.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(value = "id", allowSetters = true)
//bunu true yaptigim icin post request yaparken body'de id gonderilmeyecek, yani serialize yapilirken id kullanilmayacak
public class Spartan {

    private int id;
    private String name;
    private String gender;
    private long phone;
}
