package com.keepthinker.spring.springbootexample.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

public class SysRole implements GrantedAuthority {

    private Long id;
    private String roleName;
    private String roleDesc;

    //标记该字段不做json处理
    @JsonIgnore
    @Override
    public String getAuthority() {
        return roleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }
}
