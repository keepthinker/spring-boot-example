package com.keepthinker.spring.springbootexample.mongo;

import com.keepthinker.spring.springbootexample.entity.Country;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CountryRepository extends MongoRepository<Country, Integer> {

    void deleteByName(String name);

    @Query("{ name : ?0 , population: {'$gt' : ?1}}" )
    List<Country> getByNameAndPopulationGreater(String country, Long greaterThanPopulation);

    @Query(value = "{population: {'$gt' : ?0}}", fields = "{name : 1}" )
    List<Country> getNamesByPopulationGreater(Long greaterThanPopulation);

}

