package com.suen.ssm.service;

import com.github.pagehelper.PageInfo;
import com.suen.ssm.pojo.SysUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/*
 * 接口继承一个类
 * */
public interface SysUserService extends UserDetailsService {

        PageInfo<SysUser> queryUserList(Integer pageNum, Integer pageSize) throws Exception;

        void saveUser(SysUser sysUser) throws Exception;

        SysUser queryUserById(Long id) throws Exception;

        void addRoleToUser(Long userId, Long[] roleIds) throws Exception;
}
