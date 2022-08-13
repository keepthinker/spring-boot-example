package com.keepthinker.spring.springbootexample.mongo;

import com.keepthinker.spring.springbootexample.entity.Country;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CountryRepository extends MongoRepository<Country, Integer> {
}
