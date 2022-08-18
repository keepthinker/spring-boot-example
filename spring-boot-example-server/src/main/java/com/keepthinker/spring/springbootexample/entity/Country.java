package com.keepthinker.spring.springbootexample.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Country {
    private String name;
    private Long population;
    private String continent;
    private Date establishedDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public Date getEstablishedDate() {
        return establishedDate;
    }

    public void setEstablishedDate(Date establishedDate) {
        this.establishedDate = establishedDate;
    }
}
