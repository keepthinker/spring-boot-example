package com.keepthinker.spring.springbootexample.mongo;

import com.keepthinker.spring.springbootexample.entity.Country;

import java.util.Date;
import java.util.List;

public interface CountryDao {
    void save(Country country);
    void deleteByName(String name);
    List<Country> listAfterEstablishedTime(Date date);
}
