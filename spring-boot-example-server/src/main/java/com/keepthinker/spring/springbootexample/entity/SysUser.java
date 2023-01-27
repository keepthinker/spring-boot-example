package com.keepthinker.spring.springbootexample.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SysUser implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private Boolean status; //用户状态，1-开启-0禁用
    private Boolean passwordNonExpired; //密码是否失效，1-可用，0-失效
    /**
     * 用户关联的所有角色
     */
    private List<SysRole> roles = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getPasswordNonExpired() {
        return passwordNonExpired;
    }

    public void setPasswordNonExpired(Boolean passwordNonExpired) {
        this.passwordNonExpired = passwordNonExpired;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    //标记该字段不做json处理
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return passwordNonExpired == null ? false : passwordNonExpired;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return status == null ? false : status;
    }

}