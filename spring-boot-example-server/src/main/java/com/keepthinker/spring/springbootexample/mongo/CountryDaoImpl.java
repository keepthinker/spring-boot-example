package com.keepthinker.spring.springbootexample.mongo;

import com.keepthinker.spring.springbootexample.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class CountryDaoImpl implements CountryDao{

    private CountryRepository countryRepository;
    private MongoTemplate mongoTemplate;

    @Autowired
    public CountryDaoImpl(CountryRepository countryRepository, MongoTemplate mongoTemplate) {
        this.countryRepository = countryRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Autowired
    public void setCountryRepository(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public void save(Country country) {
        countryRepository.save(country);
    }

    @Override
    public void deleteByName(String name) {
        countryRepository.deleteByName(name);
    }

    @Override
    public List<Country> listAfterEstablishedTime(Date date) {
        Query query = new Query();
        query.addCriteria(Criteria.where("establishedDate").gt(date));
        List<Country> list = mongoTemplate.find(query, Country.class);
        return list;
    }
}
