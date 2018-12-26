package com.suen.ssm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.suen.ssm.mapper.SysRoleMapper;
import com.suen.ssm.pojo.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysRoleServiceImpl implements SysRoleService {

        @Autowired
        private SysRoleMapper sysRoleMapper;

        @Override
        public PageInfo<SysRole> queryRoleList(Integer pageNum, Integer pageSize) throws Exception {
                PageHelper.startPage(pageNum,pageSize);
                List<SysRole> roleList = sysRoleMapper.queryRoleList();
                PageInfo<SysRole> pageInfo = new PageInfo<>(roleList);
                return pageInfo;
        }

        @Override
        public void saveRole(SysRole role) throws Exception {
                sysRoleMapper.saveRole(role);
        }

        @Override
        public List<SysRole> queryRoleListByUserId(Long id) throws Exception {
                return sysRoleMapper.queryRoleListByUserId(id);
        }

        @Override
        public void addPermissionToRole(Long roleId, Long[] permissionIds) throws Exception {
                // 1、删除原来的角色-权限关系数据
                sysRoleMapper.deleteRolePermission(roleId);
                // 2、插入新的角色-权限关系数据
                if(permissionIds != null && permissionIds.length > 0){
                        for (int i = 0; i < permissionIds.length; i++) {
                                Long permissionId = permissionIds[i];
                                sysRoleMapper.saveRolePermission(roleId,permissionId);
                        }
                }
        }
}
