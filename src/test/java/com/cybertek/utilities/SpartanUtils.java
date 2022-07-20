package com.cybertek.utilities;

import com.github.javafaker.Faker;

import java.util.LinkedHashMap;
import java.util.Map;

public class SpartanUtils {

    public static Map<String,Object> postSpartan(){

        Faker faker = new Faker();

        Map<String,Object> Spartan = new LinkedHashMap<>();

        Spartan.put("name",faker.name().firstName());
        Spartan.put("gender","Female");
        Spartan.put("phone",5556656583l);

        return Spartan;

    }


}
