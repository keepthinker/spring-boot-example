package com.keepthinker.spring.springbootexample.test;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.keepthinker.spring.springbootexample.entity.Area;
import com.keepthinker.spring.springbootexample.entity.SysUser;
import com.keepthinker.spring.springbootexample.entity.UserAreaRelation;
import com.keepthinker.spring.springbootexample.mapper.AreaMapper;
import com.keepthinker.spring.springbootexample.mapper.SysUserMapper;
import com.keepthinker.spring.springbootexample.service.UserAreaService;
import com.keepthinker.spring.springbootexample.utils.JsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MybatisTest {
    private static Logger logger = LoggerFactory.getLogger(MybatisTest.class);

    @Autowired
    private AreaMapper areaMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private UserAreaService userAreaService;

    @Test
    public void testSelect(){

        List<Map<String, Object>> list = areaMapper.selectMaps(Wrappers.lambdaQuery(Area.class).select(Area::getId).between(Area::getPopulation, 100L, 100000000L));
        logger.info("result list: {}", JsonUtils.objectToString(list));
        for (Map<String, Object> elemMap : list) {
            for (Map.Entry<String, Object> entry : elemMap.entrySet()) {
                if (entry.getValue() instanceof Integer) {
                    logger.info("select maps, key:{}, Integer: {}", entry.getKey(), entry.getValue());
                } else if (entry.getValue() instanceof Long) {
                    logger.info("select maps, key:{}, Long: {}", entry.getKey(), entry.getValue());
                } else if (entry.getValue() instanceof String) {
                    logger.info("select maps, key:{}, String: {}", entry.getKey(), entry.getValue());
                } else if (entry.getValue() instanceof Float) {
                    logger.info("select maps, key:{}, Float: {}", entry.getKey(), entry.getValue());
                } else if (entry.getValue() instanceof BigDecimal) {
                    logger.info("select maps, key:{}, BigDecimal: {}", entry.getKey(), entry.getValue());
                } else if (entry.getValue() instanceof Date) {
                    logger.info("select maps, key:{}, Date: {}", entry.getKey(), entry.getValue());
                }
            }
        }

        Long count = areaMapper.selectCount(Wrappers.lambdaQuery(Area.class).between(Area::getPopulation, 100L, 100000000L));
        logger.info("count: {}", count);

        List<Area> areaList = areaMapper.selectBatchIds(Arrays.asList(1, 2, 3, 4, 5));
        logger.info("selectBatchIds, areaList:{}", JsonUtils.objectToString(areaList));

    }

    @Test
    public void testUpdate(){
        Calendar cl = Calendar.getInstance();
        cl.set(1, Calendar.JANUARY, 1, 0, 0, 0);
        Date date = cl.getTime();
        int rowsUpdate = areaMapper.update(null, new UpdateWrapper<Area>()
                .eq("country", "China")
                .gt("established_date", date)
                .set("population", 14000010001L));
        System.out.println("rows updated: " + rowsUpdate);

        boolean updateResult = new LambdaUpdateChainWrapper<Area>(areaMapper)
                .eq(Area::getCountry, "China").set(Area::getPopulation, 14000010002L).update();

        System.out.println("updated result: " + updateResult);

    }

    @Test
    public void testSave(){
        Area area = new Area();
        area.setProvince("Jiangsu");
        area.setEstablishedDate(new Date());
        area.setCountry("China");
        area.setPopulation(80400000L);
        area.setContinent("Asia");
        areaMapper.insert(area);
        System.out.println("save result: " + JsonUtils.objectToString(area));
    }

    @Test
    public void saveAreaBatch() throws Exception{
        ThreadLocalRandom random = ThreadLocalRandom.current();
        ExecutorService executorService = Executors.newFixedThreadPool(50);
        for (int i = 0; i < 10000; i++) {
            CountDownLatch countDownLatch = new CountDownLatch(50);
            for (int j = 0; j < 50; j++) {
                Area area = new Area();
                area.setProvince("Province" + random.nextInt(1000000));
                area.setEstablishedDate(new Date());
                area.setCountry("China");
                area.setPopulation(random.nextLong(100000000));
                area.setContinent("Asia");
                executorService.execute(() -> {
                    areaMapper.insert(area);
                    countDownLatch.countDown();
                });
            }
            countDownLatch.await();
        }
    }

    @Test
    public void saveAreaRelationBatch() {

        int size = 1000000;
        List<Integer> userIdList = new ArrayList<>();
        List<Integer> areaIdList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            userIdList.add(i);
            areaIdList.add(i);
        }
        Collections.shuffle(userIdList);
        Collections.shuffle(areaIdList);
        Iterator<Integer> userIdIterator = userIdList.iterator();
        Iterator<Integer> areaIdIterator = areaIdList.iterator();

        List<UserAreaRelation> userAreaRelations = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            UserAreaRelation userAreaRelation = new UserAreaRelation();
            userAreaRelation.setUserId(userIdIterator.next());
            userAreaRelation.setAreaId(areaIdIterator.next());
            userAreaRelations.add(userAreaRelation);

            if (userAreaRelations.size() == 1000) {
                userAreaService.saveBatch(userAreaRelations);
                userAreaRelations.clear();
            }
        }

        userAreaService.saveBatch(userAreaRelations);
        userAreaRelations.clear();

    }

    @Test
    public void testSaveSysUser() {
        SysUser sysUser = new SysUser();
        sysUser.setUsername("username");
        sysUser.setPassword("password");
        sysUser.setStatus(true);
        sysUser.setPasswordNonExpired(true);
        sysUserMapper.insert(sysUser);
    }

}
