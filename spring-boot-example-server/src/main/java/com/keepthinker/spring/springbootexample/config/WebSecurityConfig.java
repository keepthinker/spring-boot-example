package com.keepthinker.spring.springbootexample.config;

import com.keepthinker.spring.springbootexample.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SysUserService userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // 1.指定认证对象的来源(内存或者数据库),指定加密方式
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

    //2. SpringSecurity配置相关信息
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 释放静态资源，指定拦截规则，指定自定义的认证和退出页面，csrf配置等
        http.csrf().disable()
                .authorizeRequests()
                // 指定拦截规则
                .antMatchers("/failure.html",
                        "/css/**",
                        "/img/**",
                        "/areas",
                        "/login.html",
                        "/login-page",
                        "/login",
                        "/register").permitAll()  //释放这些资源，不拦截
                .antMatchers("/**").hasAnyRole("USER", "ADMIN") //所有资源都需要这些角色中的一个
                .anyRequest().authenticated()  //其他请求，必须认证通过之后才能访问
                .and()  // 表示新的一个配置开始
                // 指定自定义的认证页面
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .successForwardUrl("/homepage")
                .failureForwardUrl("/failure.html")
                .permitAll() // 释放这些资源，不拦截登录
                .and()
                // 指定自定义的退出页面
                .logout()
                .logoutSuccessUrl("/logout")
                .invalidateHttpSession(true) // 清楚session
                .logoutSuccessUrl("/login.jsp")
                .permitAll()
        //.and()
//                禁用csrf配置，默认开启的（一般不写，页面要加csrf），这里我们测试下
//                .and()
//                .csrf()
//                .disable()
        ;
    }
}
