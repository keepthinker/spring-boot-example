package com.keepthinker.spring.springbootexample.service;

import com.keepthinker.spring.springbootexample.entity.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface SysUserService extends UserDetailsService {

    void save(SysUser user);
}