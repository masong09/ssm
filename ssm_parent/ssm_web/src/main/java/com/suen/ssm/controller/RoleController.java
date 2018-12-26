package com.suen.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.suen.ssm.pojo.SysPermission;
import com.suen.ssm.pojo.SysRole;
import com.suen.ssm.service.SysPermissionService;
import com.suen.ssm.service.SysRoleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("role")
public class RoleController {

        @Autowired
        private SysRoleService sysRoleService;

        @Autowired
        private SysPermissionService sysPermissionService;

        @RequestMapping("queryRoleList")
        public String queryRoleList(@RequestParam(defaultValue = "1")Integer pageNum, @RequestParam(defaultValue = "5")Integer pageSize, Model model) throws Exception{
                PageInfo<SysRole> pageInfo = sysRoleService.queryRoleList(pageNum, pageSize);
                model.addAttribute("pageInfo",pageInfo);
                return "role-list";
        }

        @RequestMapping("toRoleAdd")
        public String toRoleAdd(){
                return "role-add";
        }

        @RequestMapping("saveRole")
        public String saveRole(SysRole role) throws Exception{
                sysRoleService.saveRole(role);
                return "redirect:queryRoleList.action";
        }

        @RequestMapping("toRolePermissionAdd")
        public String toRolePermissionAdd(Long id,Model model, @RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "10") Integer pageSize) throws Exception{
                //查询全部权限信息，供选择使用
                PageInfo<SysPermission> pageInfo = sysPermissionService.queryPermissionList(pageNum, pageSize);
                //根据角色id查询该角色拥有的全部权限数据
                List<SysPermission> currentPermissionList = sysPermissionService.queryPermissionListByRoleId(id);
                //取出当前用户所拥有的角色的id，放入数值型集合currentPermissionIds中，便于前端代码处理
                List<Long> currentPermissionIds = new ArrayList<>();
                if (currentPermissionList !=null && currentPermissionList.size() > 0){
                        for (int i = 0; i < currentPermissionList.size(); i++) {
                                Long permissionId = currentPermissionList.get(i).getId();
                                currentPermissionIds.add(permissionId);
                        }
                }
                model.addAttribute("pageInfo",pageInfo);
                model.addAttribute("currentPermissionIds",currentPermissionIds);
                //回传roleId
                model.addAttribute("roleId",id);
                return "role-permission-add";
        }

        @RequestMapping("addPermissionToRole")
        public String addPermissionToRole(Long roleId,Long[] permissionIds) throws Exception{
                sysRoleService.addPermissionToRole(roleId,permissionIds);
                return "redirect:queryRoleList.action";
        }
}
