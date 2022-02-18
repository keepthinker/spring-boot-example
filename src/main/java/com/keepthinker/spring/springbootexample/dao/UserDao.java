package com.keepthinker.spring.springbootexample.dao;

import com.keepthinker.spring.springbootexample.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface UserDao {
    List<User> findAll();
//    public List<User> findAll(){
//        List<User> result = new ArrayList<>();
//        User user = new User();
//        user.setUser("my user");
//        result.add(user);
//        return result;
//    }

}
