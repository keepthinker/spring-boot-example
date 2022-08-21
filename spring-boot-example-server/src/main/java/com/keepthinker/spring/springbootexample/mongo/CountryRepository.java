package com.keepthinker.spring.springbootexample.mongo;

import com.keepthinker.spring.springbootexample.entity.Area;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CountryRepository extends MongoRepository<Area, Integer> {

    void deleteByCountry(String Country);

    @Query("{ country : ?0 , population: {'$gt' : ?1}}")
    List<Area> getByCountryAndPopulationGreater(String country, Long greaterThanPopulation);

    /**
     * 按value查询条件，?符号后的数字是入参对应的位置，fields为1表示，返回哪些字段
     */
    @Query(value = "{population: {'$gt' : ?0}}", fields = "{country : 1}")
    List<Area> getCountrysByPopulationGreater(Long greaterThanPopulation);

    @DeleteQuery("{population : {'$lt' : ?0}}")
    void deleteLessThan(Long lessThanPopulation);

}

