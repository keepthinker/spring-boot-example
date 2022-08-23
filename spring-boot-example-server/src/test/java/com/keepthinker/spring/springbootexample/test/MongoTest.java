package com.keepthinker.spring.springbootexample.test;

import com.keepthinker.spring.springbootexample.entity.ContinentCountryPopulation;
import com.keepthinker.spring.springbootexample.entity.Area;
import com.keepthinker.spring.springbootexample.mongo.AreaDao;
import com.keepthinker.spring.springbootexample.mongo.AreaRepository;
import com.keepthinker.spring.springbootexample.mongo.IdGenerator;
import com.keepthinker.spring.springbootexample.utils.DateUtils;
import com.keepthinker.spring.springbootexample.utils.JsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MongoTest {
    private static Logger logger = LoggerFactory.getLogger(MongoTest.class);

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private AreaDao areaDao;

    @Autowired
    private IdGenerator idGenerator;

    @Test
    public void generateAreas(){
        Area area = new Area();
        area.setContinent("Asia");
        area.setCountry("China");
        area.setPopulation(110_000_000L);
        area.setEstablishedDate(DateUtils.parse("1949-10-01", "yyyy-MM-dd"));
        area.setId(idGenerator.generateId(Area.collectionName()));
        area.setProvince("Guangdong");
        areaRepository.save(area);


        area = new Area();
        area.setContinent("Asia");
        area.setCountry("China");
        area.setPopulation(40_000_000L);
        area.setEstablishedDate(DateUtils.parse("1949-10-01", "yyyy-MM-dd"));
        area.setId(idGenerator.generateId(Area.collectionName()));
        area.setProvince("Fujian");
        areaRepository.save(area);


        area = new Area();
        area.setContinent("North America");
        area.setCountry("USA");
        area.setPopulation(39_000_000L);
        area.setEstablishedDate(DateUtils.parse("1850-09-09", "yyyy-MM-dd"));
        area.setId(idGenerator.generateId(Area.collectionName()));
        area.setProvince("California");
        areaRepository.save(area);

        area = new Area();
        area.setContinent("North America");
        area.setCountry("USA");
        area.setPopulation(20_215_751L);
        area.setEstablishedDate(DateUtils.parse("1776-07-04", "yyyy-MM-dd"));
        area.setId(idGenerator.generateId(Area.collectionName()));
        area.setProvince("New York");
        areaRepository.save(area);



    }


    @Test
    public void testGetMongoRecords() {
        List<Area> areaList = areaRepository.findAll();
        logger.info("areaList findAll:{}", JsonUtils.objectToString(areaList));

        areaList = areaDao.listAfterEstablishedTime(DateUtils.parse("1850-09-08", "yyyy-MM-dd"), 3);
        logger.info("areaList listAfterEstablishedTime:{}", JsonUtils.objectToString(areaList));

        areaList = areaRepository.getByCountryAndPopulationGreater("USA", 10000000L);
        logger.info("areaList getByNameAndPopulationGreater:{}", JsonUtils.objectToString(areaList));


        areaList = areaRepository.getCountrysByPopulationGreater(10000000L);
        logger.info("areaList getNamesByPopulationGreater:{}", JsonUtils.objectToString(areaList));

    }

    @Test
    public void saveAndDelete(){

        Area area = new Area();
        Calendar calendar = Calendar.getInstance();
        calendar.set(1867, Calendar.JULY, 1, 0, 0, 0);
        area.setId(9999);
        area.setEstablishedDate(calendar.getTime());
        area.setContinent("North America");
        area.setCountry("Canada");
        area.setPopulation(1000L);
        areaRepository.save(area);
        logger.info("area saved:{}", JsonUtils.objectToString(area));

        areaRepository.deleteLessThan(2000L);

        logger.info("area delete:{}", area.getId());
    }

    @Test
    public void generateId() {
        int threads = 50;
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        CountDownLatch latch = new CountDownLatch(threads);
        for (int i = 0; i < 100; i++) {
            executor.execute(() -> {
                logger.info("area _id:{}", idGenerator.generateId("Country"));
                logger.info("area _id:{}", idGenerator.generateId("Country"));
                logger.info("area _id:{}", idGenerator.generateId("Country"));
                latch.countDown();
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void listContinentPopulation() {
        List<ContinentCountryPopulation> list = areaDao.listContinentCountryPopulation();
        for (ContinentCountryPopulation continentPopulation : list) {
            logger.info("listContinentCountryPopulation, value:{}", JsonUtils.objectToString(continentPopulation));
        }
    }
}
