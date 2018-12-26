package com.suen.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.suen.ssm.pojo.SysRole;
import com.suen.ssm.pojo.SysUser;
import com.suen.ssm.service.SysRoleService;
import com.suen.ssm.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("user")
@Secured("ROLE_ADMIN")
public class UserController {

        @Autowired
        private SysUserService sysUserService;

        @Autowired
        private SysRoleService sysRoleService;

        /**
         * 获取当前登录用户的用户名
         * @return
         */
        @RequestMapping("showUsername")
        @ResponseBody   //将对象转换成json串
        public String showUsername(HttpSession session){
                //方式一：从当前线程获取SecurityContext对象
                SecurityContext securityContext = SecurityContextHolder.getContext();

                //方式二：从session中获取SecurityContext对象
                //用于Header.jsp中通过EL表达式获取用户名
                SecurityContext spring_security_context = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
//                System.out.println("判断securityContext和spring_security_context是否为同一个对象：" + (securityContext==spring_security_context));

                //Authentication：认证（是securityContext对象的一个属性）
                Authentication authentication = securityContext.getAuthentication();
                //Principal主角对象就是User对象
                User user = (User) authentication.getPrincipal();
                return user.getUsername();
        }


        /**
         *  查询全部用户+PageHelper分页
         *  每次分页操作需要从前台传递到后台的参数：
         *      pageNum(当前页)，pageSize(每页显示的记录数)
         * @param model
         */
        @RequestMapping("queryUserList")
        public String queryUserList(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize, Model model) throws Exception {
                PageInfo<SysUser> pageInfo = sysUserService.queryUserList(pageNum,pageSize);
                model.addAttribute("pageInfo",pageInfo);
                return "user-list";
        }

        /**
         * 跳转到添加用户页面
         * @return
         */
        @RequestMapping("toUserAdd")
        public String toUserAdd(){
                return "user-add";
        }

        /**
         * 添加用户
         * @param sysUser
         * @return
         * @throws Exception
         */
        @RequestMapping("saveUser")
        public String saveUser(SysUser sysUser) throws Exception{
                sysUserService.saveUser(sysUser);
                return "redirect:queryUserList.action";
        }

        /**
         * 根据id查询用户详情信息
         * @param id
         * @param model
         * @return
         * @throws Exception
         */
        @RequestMapping("queryUserById")
        public String queryUserById(Long id,Model model) throws Exception{
                SysUser user = sysUserService.queryUserById(id);
                model.addAttribute("user",user);
                return "user-show";
        }

        @RequestMapping("toUserRoleAdd")
        public String toUserRoleAdd(@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "10") Integer pageSize,Long id,Model model) throws Exception{
                //查询全部角色信息，供添加角色使用
                PageInfo<SysRole> pageInfo = sysRoleService.queryRoleList(pageNum, pageSize);
                //根据id查询当前用户所拥有的角色数据
                List<SysRole> currentRoleList = sysRoleService.queryRoleListByUserId(id);
                //取出当前用户所拥有的角色的id，放入数值型集合currentRoleIds中，便于前端代码处理
                List<Long> currentRoleIds = new ArrayList<>();
                if (currentRoleList != null && currentRoleList.size() > 0){
                        for (int i = 0; i < currentRoleList.size(); i++) {
                                Long currentId =  currentRoleList.get(i).getId();
                                currentRoleIds.add(currentId);
                        }
                }
                model.addAttribute("pageInfo",pageInfo);
                model.addAttribute("currentRoleIds",currentRoleIds);
                model.addAttribute("userId",id);
                return "user-role-add";
        }

        /**
         * 保存最新的用户-角色信息到中间表
         * 1、删除旧的关系
         * 2、插入新的关系
         * @param userId        当前用户id
         * @param roleIds       角色id数组
         * @return
         */
        @RequestMapping("addRoleToUser")
        public String addRoleToUser(Long userId,Long[] roleIds) throws Exception {
                sysUserService.addRoleToUser(userId,roleIds);
                return "redirect:queryUserList.action";
        }
}
