package com.suen.ssm.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色表
 */
public class SysRole {

    private Long id;
    private String roleName;
    private String roleDesc;

    //该角色对象持有的用户对象集合
    private List<SysUser> sysUserList = new ArrayList<>();
    //该角色对象持有的权限对象集合
    private List<SysPermission> sysPermissionList = new ArrayList<>();

    public List<SysUser> getSysUserList() {
        return sysUserList;
    }

    public void setSysUserList(List<SysUser> sysUserList) {
        this.sysUserList = sysUserList;
    }

    public List<SysPermission> getSysPermissionList() {
        return sysPermissionList;
    }

    public void setSysPermissionList(List<SysPermission> sysPermissionList) {
        this.sysPermissionList = sysPermissionList;
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

    @Override
    public String toString() {
        return "SysRole{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                '}';
    }
}
