package com.keepthinker.spring.springbootexample.dubbo;
import com.keepthinker.spring.springbootexample.dto.User;

import java.util.List;

public interface UserFacade {
    List<User> getAllUsers();
}
