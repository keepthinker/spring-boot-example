package com.keepthinker.spring.springbootexample.test;

import com.keepthinker.spring.springbootexample.entity.Country;
import com.keepthinker.spring.springbootexample.mongo.CountryDao;
import com.keepthinker.spring.springbootexample.mongo.CountryRepository;
import com.keepthinker.spring.springbootexample.utils.JsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MongoTest {
    private static Logger logger = LoggerFactory.getLogger(MongoTest.class);

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CountryDao countryDao;
    @Test
    public void testGetMongoRecords(){
        List<Country> countryList = countryRepository.findAll();
        logger.info("countryList findAll:{}", JsonUtils.objectToString(countryList));

        countryList = countryDao.listAfterEstablishedTime(new Date(System.currentTimeMillis() - 3600*24));
        logger.info("countryList listAfterEstablishedTime:{}", JsonUtils.objectToString(countryList));

        countryList = countryRepository.getByNameAndPopulationGreater("India", 10000000L);
        logger.info("countryList getByNameAndPopulationGreater:{}", JsonUtils.objectToString(countryList));


        countryList = countryRepository.getNamesByPopulationGreater(10000000L);
        logger.info("countryList getNamesByPopulationGreater:{}", countryList);


    }
}
