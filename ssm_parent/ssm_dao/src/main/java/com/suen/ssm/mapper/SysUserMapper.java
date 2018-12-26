package com.suen.ssm.mapper;

import com.suen.ssm.pojo.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper {
        SysUser querySysUserByUsername(String username) throws Exception;

        List<SysUser> queryUserList() throws Exception;

        void saveUser(SysUser sysUser) throws Exception;

        SysUser queryUserById(Long id) throws Exception;

        void deleteUserRoleByUserId(Long userId) throws Exception;

        void saveUserRole(@Param("userId")Long userId, @Param("roleId")Long roleId) throws Exception;
}
