package com.keepthinker.spring.springbootexample.mongo;

import com.keepthinker.spring.springbootexample.entity.ContinentCountryPopulation;
import com.keepthinker.spring.springbootexample.entity.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOptions;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    public void save(Area country) {
        countryRepository.save(country);
    }

    @Override
    public void deleteByCountry(String country) {
        countryRepository.deleteByCountry(country);
    }

    @Override
    public List<Area> listAfterEstablishedTime(Date date, int limitSize) {
        Query query = new Query();
        query.addCriteria(Criteria.where("establishedDate").gt(date));
        query.limit(limitSize);

        List<Area> list = mongoTemplate.find(query, Area.class);
        return list;
    }
    /*
      todo select * from table_name
      select field_1, field_2 from table_name where field_3 > 10 and field_4 > '2020-01-02 00:30:00' limit 3, 5
      select field_1, count(*) from table_name group by field_1
      select field_1, sum(field_2) from table_name group by field_1
      select * from table_name where _id in (1, 2, 3)
     */

    public List<Area> listByIds(Integer... ids){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").in(Arrays.stream(ids).map(v -> (Object)v).collect(Collectors.toList())));
        return mongoTemplate.find(query, Area.class);
    }

    /**
     * the same as sql: "select continent, country, sum(population) as population from country group by continent, country"
     * @return
     */
    public List<ContinentCountryPopulation> listContinentCountryPopulation() {

        AggregationOptions options = AggregationOptions.builder().build();

        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group("continent", "country").sum("population").as("population"),
                Aggregation.project("population").and("idWrapper").previousOperation()
        ).withOptions(options);

        AggregationResults<ContinentCountryPopulation> results = mongoTemplate.aggregate
                (aggregation, "area", ContinentCountryPopulation.class);
        return results.getMappedResults();
    }

}
