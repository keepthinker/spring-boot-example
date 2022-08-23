package com.keepthinker.spring.springbootexample.mongo;

import com.keepthinker.spring.springbootexample.entity.ContinentCountryPopulation;
import com.keepthinker.spring.springbootexample.entity.Area;

import java.util.Date;
import java.util.List;

public interface AreaDao {
    void save(Area country);
    void deleteByCountry(String country);
    List<Area> listAfterEstablishedTime(Date date, int limitSize);
    List<ContinentCountryPopulation> listContinentCountryPopulation();

}
