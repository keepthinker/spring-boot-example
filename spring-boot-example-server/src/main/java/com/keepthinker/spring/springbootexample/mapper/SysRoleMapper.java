package com.keepthinker.spring.springbootexample.mapper;


import com.keepthinker.spring.springbootexample.entity.SysRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    List<SysRole> listAllByUserId(Long userId);
}