package com.suen.ssm.service;

import com.github.pagehelper.PageInfo;
import com.suen.ssm.pojo.SysPermission;

import java.util.List;

public interface SysPermissionService {
        PageInfo<SysPermission> queryPermissionList(Integer pageNum, Integer pageSize) throws Exception;

        void savePermission(SysPermission permission) throws Exception;

        List<SysPermission> queryPermissionParent() throws Exception;

        List<SysPermission> queryPermissionListByRoleId(Long id) throws Exception;
}