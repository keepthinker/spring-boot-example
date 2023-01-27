package com.keepthinker.spring.springbootexample.controller;

import com.keepthinker.spring.springbootexample.entity.SysRole;
import com.keepthinker.spring.springbootexample.entity.SysUser;
import com.keepthinker.spring.springbootexample.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = "login-page", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "homepage")
    public String homepage() {
        return "redirect:/homepage.jsp";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public @ResponseBody String register(@RequestBody SysUser sysUser) {
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("ADMIN");
        sysRole.setRoleDesc("系统管理员");
        sysUser.getRoles().add(sysRole);
        sysUserService.save(sysUser);
        return "{\"code\": 0}";
    }
}
