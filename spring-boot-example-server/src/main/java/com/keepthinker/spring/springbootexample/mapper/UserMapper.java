package com.keepthinker.spring.springbootexample.mapper;

import com.keepthinker.spring.springbootexample.dto.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    List<User> getUsers(@Param("begin") int begin, @Param("size") int size);
    void insert(User user);
    void insertBatch(List<User> users);
    void update(User user);
    int count();
}
