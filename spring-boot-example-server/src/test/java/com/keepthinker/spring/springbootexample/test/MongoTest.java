package com.keepthinker.spring.springbootexample.test;

import com.keepthinker.spring.springbootexample.entity.Country;
import com.keepthinker.spring.springbootexample.mongo.CountryRepository;
import com.keepthinker.spring.springbootexample.utils.JsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MongoTest {
    private static Logger logger = LoggerFactory.getLogger(MongoTest.class);

    @Autowired
    private CountryRepository countryRepository;
    @Test
    public void testGetMongoRecords(){
        List<Country> countryList = countryRepository.findAll();
        logger.info("countryList:{}", JsonUtils.objectToString(countryList));
    }
}
