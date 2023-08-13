package com.keepthinker.spring.springbootexample.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.keepthinker.spring.springbootexample.entity.UserAreaRelation;
import com.keepthinker.spring.springbootexample.mapper.UserAreaRelationMapper;
import com.keepthinker.spring.springbootexample.service.UserAreaService;
import org.springframework.stereotype.Service;

@Service
public class UserAreaServiceImpl extends ServiceImpl<UserAreaRelationMapper, UserAreaRelation> implements UserAreaService {
}
