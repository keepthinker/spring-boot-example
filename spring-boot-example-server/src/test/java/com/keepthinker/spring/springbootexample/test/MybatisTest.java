package com.keepthinker.spring.springbootexample.test;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.keepthinker.spring.springbootexample.entity.Area;
import com.keepthinker.spring.springbootexample.mapper.AreaMapper;
import com.keepthinker.spring.springbootexample.utils.JsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MybatisTest {
    private static Logger logger = LoggerFactory.getLogger(MybatisTest.class);

    @Autowired
    private AreaMapper areaMapper;

    @Test
    public void testSelect(){

        java.util.List<java.util.Map<String, Object>> list = areaMapper.selectMaps(Wrappers.lambdaQuery(Area.class).select(Area::getId).between(Area::getPopulation, 100L, 100000000L));
        logger.info("result list: {}", JsonUtils.objectToString(list));

        Long count = areaMapper.selectCount(Wrappers.lambdaQuery(Area.class).between(Area::getPopulation, 100L, 100000000L));
        logger.info("count: {}", count);

    }
}
