package com.keepthinker.spring.springbootexample.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Country {
    private String name;
    private Integer populatioin;
    private String Continent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPopulatioin() {
        return populatioin;
    }

    public void setPopulatioin(Integer populatioin) {
        this.populatioin = populatioin;
    }

    public String getContinent() {
        return Continent;
    }

    public void setContinent(String continent) {
        Continent = continent;
    }
}
