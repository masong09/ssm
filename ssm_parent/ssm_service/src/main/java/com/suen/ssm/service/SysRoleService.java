package com.suen.ssm.service;

import com.github.pagehelper.PageInfo;
import com.suen.ssm.pojo.SysRole;

import java.util.List;


public interface SysRoleService {

        PageInfo<SysRole> queryRoleList(Integer pageNum, Integer pageSize) throws Exception;

        void saveRole(SysRole role) throws Exception;

        List<SysRole> queryRoleListByUserId(Long id) throws Exception;

        void addPermissionToRole(Long roleId, Long[] permissionIds) throws Exception;
}
