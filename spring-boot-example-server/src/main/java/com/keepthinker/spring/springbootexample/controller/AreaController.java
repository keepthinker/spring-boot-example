package com.keepthinker.spring.springbootexample.controller;

import com.keepthinker.spring.springbootexample.entity.Area;
import com.keepthinker.spring.springbootexample.mongo.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AreaController {
    @Autowired
    private AreaRepository areaService;

    @RequestMapping("/areas")
    public List<Area> getAllCountries() {
        return areaService.findAll();
    }
}
