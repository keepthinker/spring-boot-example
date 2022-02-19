package com.keepthinker.spring.springbootexample.mapper;

import com.keepthinker.spring.springbootexample.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    List<User> findAll();

}
