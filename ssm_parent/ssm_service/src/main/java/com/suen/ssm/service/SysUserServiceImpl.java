package com.suen.ssm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.suen.ssm.mapper.SysUserMapper;
import com.suen.ssm.pojo.SysRole;
import com.suen.ssm.pojo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class SysUserServiceImpl implements SysUserService {

        @Autowired
        private SysUserMapper sysUserMapper;

        @Autowired
        private SysRoleService sysRoleService;

        /*注入BCrypt加密算法工具类对象*/
        @Autowired
        private BCryptPasswordEncoder bCryptPasswordEncoder;

        /**
         * 加载用户名显示并在页面上
         * @param username
         * @return User user：UserDetails的实现类对象（包含用户名、密码、权限等信息）
         * @throws UsernameNotFoundException
         */
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                // 从数据库查询数据，返回即可（根据用户名查询用户记录）
                //User：不是自定义的pojo对象，而是org.springframework.security.core.userdetails.User;
                User user = null;
                try {
                        SysUser loginUser = sysUserMapper.querySysUserByUsername(username);

                        //根据id查询出当前用户所拥有的角色的集合
                        Long userId = loginUser.getId();
                        List<SysRole> sysRoleList = sysRoleService.queryRoleListByUserId(userId);

                        // 角色的集合定义（因为一个人可能有多个角色）
                        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

                        if (sysRoleList != null && sysRoleList.size() > 0) {
                                for (int i = 0; i < sysRoleList.size(); i++) {
                                        SysRole sysRole = sysRoleList.get(i);
                                        String roleName = sysRole.getRoleName();
                                        grantedAuthorityList.add(new SimpleGrantedAuthority(roleName));
                                }
                        }
                        //{noop}：是一个标识，表示密码不能明文
//                        user = new User(username,"{noop}" + loginUser.getPassword(),grantedAuthorityList);
                        //指定加密算法后，需要把{noop}字符串去掉
                        user = new User(username,loginUser.getPassword(),grantedAuthorityList);
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return user;
        }

        /**
         * 查询全部用户+PageHelper分页
         * @param pageNum
         * @param pageSize
         * @return
         * @throws Exception
         */
        @Override
        public PageInfo<SysUser> queryUserList(Integer pageNum, Integer pageSize) throws Exception {
                PageHelper.startPage(pageNum,pageSize);
                List<SysUser> userList = sysUserMapper.queryUserList();
                PageInfo<SysUser> pageInfo = new PageInfo<>(userList);
                return pageInfo;
        }

        /**
         * 新增用户，密码用BCrypt工具类加密
         * @param sysUser
         * @throws Exception
         */
        @Override
        public void saveUser(SysUser sysUser) throws Exception {
                //把password用BCryptPasswordEncoder加密算法工具类的encode方法进行加密处理
                String encodePassword = bCryptPasswordEncoder.encode(sysUser.getPassword());
                sysUser.setPassword(encodePassword);
                sysUserMapper.saveUser(sysUser);
        }

        @Override
        public SysUser queryUserById(Long id) throws Exception {
                return sysUserMapper.queryUserById(id);
        }

        /**
         * 保存最新的用户-角色信息到中间表
         * 1、根据用户id删除旧的关系
         * 2、插入新的用户-角色关系
         */
        @Override
        public void addRoleToUser(Long userId, Long[] roleIds) throws Exception {
                //1、根据用户id删除旧的关系
                sysUserMapper.deleteUserRoleByUserId(userId);
                //2、插入新的用户-角色关系
                if (roleIds !=null && roleIds.length > 0){
                        for (int i = 0; i < roleIds.length; i++) {
                                Long roleId = roleIds[i];
                                sysUserMapper.saveUserRole(userId,roleId);
                        }
                }
        }
}
