package com.keepthinker.spring.springbootexample.controller;

import com.keepthinker.spring.springbootexample.entity.Country;
import com.keepthinker.spring.springbootexample.mongo.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryController {
    @Autowired
    private CountryRepository countryService;

    @RequestMapping("/contries")
    public List<Country> getAllCountries() {
        return countryService.findAll();
    }
}
