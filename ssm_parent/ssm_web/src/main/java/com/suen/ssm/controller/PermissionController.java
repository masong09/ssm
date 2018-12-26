package com.suen.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.suen.ssm.pojo.SysPermission;
import com.suen.ssm.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("permission")
public class PermissionController {

        @Autowired
        private SysPermissionService sysPermissionService;

        @RequestMapping("queryPermissionList")
        public String queryPermissionList(@RequestParam(defaultValue = "1")Integer pageNum, @RequestParam(defaultValue = "5")Integer pageSize, Model model) throws Exception{
                PageInfo<SysPermission> pageInfo = sysPermissionService.queryPermissionList(pageNum,pageSize);
                model.addAttribute("pageInfo",pageInfo);
                return "permission-list";
        }

        /**
         * 跳转权限添加页面
         * @return
         */
        @RequestMapping("toPermissionAdd")
        public String toPermissionAdd(Model model) throws Exception{
                List<SysPermission> parentList = sysPermissionService.queryPermissionParent();
                model.addAttribute("parentList",parentList);
                return "permission-add";
        }

        @RequestMapping("savePermission")
        public String savePermission(SysPermission permission) throws Exception{
                sysPermissionService.savePermission(permission);
                return "redirect:queryPermissionList.action";
        }


}
