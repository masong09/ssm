package com.suen.ssm.mapper;

import com.suen.ssm.pojo.SysPermission;

import java.util.List;

public interface SysPermissionMapper {
        List<SysPermission> queryPermissionList() throws Exception;

        void savePermission(SysPermission permission) throws Exception;

        List<SysPermission> queryPermissionParent() throws Exception;

        List<SysPermission> queryPermissionListByRoleId(Long id) throws Exception;
}
