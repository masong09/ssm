package com.suen.ssm.mapper;

import com.suen.ssm.pojo.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper {
        List<SysRole> queryRoleList() throws Exception;

        void saveRole(SysRole role) throws Exception;

        List<SysRole> queryRoleListByUserId(Long id) throws Exception;

        void deleteRolePermission(Long roleId) throws Exception;

        void saveRolePermission(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId) throws Exception;
}
