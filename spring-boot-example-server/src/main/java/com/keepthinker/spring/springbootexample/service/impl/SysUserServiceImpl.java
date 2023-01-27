package com.keepthinker.spring.springbootexample.service.impl;

import com.alibaba.nacos.shaded.org.checkerframework.checker.units.qual.A;
import com.keepthinker.spring.springbootexample.entity.SysRole;
import com.keepthinker.spring.springbootexample.entity.SysUser;
import com.keepthinker.spring.springbootexample.entity.SysUserRole;
import com.keepthinker.spring.springbootexample.mapper.SysRoleMapper;
import com.keepthinker.spring.springbootexample.mapper.SysUserMapper;
import com.keepthinker.spring.springbootexample.mapper.SysUserRoleMapper;
import com.keepthinker.spring.springbootexample.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public void save(SysUser sysUser) {
		// 将密码加密入库
		sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
		sysUserMapper.insertSelective(sysUser);

		for (SysRole role : sysUser.getRoles()) {
			sysRoleMapper.insertSelective(role);
			SysUserRole sysUserRole = new SysUserRole();
			sysUserRole.setUserId(sysUser.getId());
			sysUserRole.setRoleId(role.getId());
			sysUserRole.setEnabled(true);
			sysUserRoleMapper.insertSelective(sysUserRole);
		}

	}

	/**
	 * 认证业务
	 *
			 * @param username
	 *            - 用户在浏览器输入的用户名
	 * @return UserDetails - Spring Security的用户对象，返回 null表示认证失败！
			* @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		/**
		 * 用户信息和角色信息可以一步关联查询到位得到SysUser，我这里分开查询
		 */
		// 1.查询用户
		SysUser sysUser = sysUserMapper.getByUsername(username);
		if (sysUser == null) {
			return null;
		}
		// 2.获取用户关联的所有角色
		List<SysRole> sysRoles = sysRoleMapper.listAllByUserId(sysUser.getId());
		sysUser.setRoles(sysRoles);
		System.out.println("====> sysUser=" + sysUser.toString());
		return sysUser;
	}
}
