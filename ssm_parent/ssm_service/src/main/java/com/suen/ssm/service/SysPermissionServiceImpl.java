package com.suen.ssm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.suen.ssm.mapper.SysPermissionMapper;
import com.suen.ssm.pojo.SysPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysPermissionServiceImpl implements SysPermissionService {

        @Autowired
        private SysPermissionMapper sysPermissionMapper;

        @Override
        public PageInfo<SysPermission> queryPermissionList(Integer pageNum, Integer pageSize) throws Exception {
                PageHelper.startPage(pageNum,pageSize);
                List<SysPermission> permissionList = sysPermissionMapper.queryPermissionList();
                PageInfo<SysPermission> pageInfo = new PageInfo<>(permissionList);
                return pageInfo;
        }

        @Override
        public void savePermission(SysPermission permission) throws Exception {
                sysPermissionMapper.savePermission(permission);
        }

        @Override
        public List<SysPermission> queryPermissionParent() throws Exception {
                return sysPermissionMapper.queryPermissionParent();
        }

        @Override
        public List<SysPermission> queryPermissionListByRoleId(Long id) throws Exception {
                return sysPermissionMapper.queryPermissionListByRoleId(id);
        }
}
