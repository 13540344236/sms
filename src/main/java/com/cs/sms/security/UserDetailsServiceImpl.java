//package com.cs.sms.security;
//
//
//import com.cs.sms.mapper.UserMapper;
//import com.cs.sms.pojo.vo.UserLoginVO;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Security自动调用的UserDetailsService实现类
// *
// * @author java@tedu.cn
// * @version 0.0.1
// */
//@Slf4j
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private UserMapper userMapper;
//
//    public UserDetailsServiceImpl() {
//        log.debug("创建Security框架自动调用的UserDetailsService实现类对象：UserDetailsServiceImpl");
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        log.debug("Spring Security自动根据用户名【{}】查询用户详情", s);
//
//        // 执行查询
//        UserLoginVO user = userMapper.getByUsername(s);
//        // 判断是否查询到有效结果
//        if (user != null) {
//            log.debug("查询到匹配的管理员信息：{}", user);
//
//            // 将查询结果中的权限信息转换成Security要求的格式
//            List<String> permissions = user.getPermissions();
//            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//            for (String permission : permissions) {
//                authorities.add(new SimpleGrantedAuthority(permission));
//            }
//
//            // 返回自定义的UserDetails类型的对象
//            com.cs.sms.security.UserDetails userDetails = new com.cs.sms.security.UserDetails(
//                    user.getUsername(),
//                    user.getPassword(),
//                    user.getEnable() == 1,
//                    authorities
//            );
//            userDetails.setId(user.getId());
//            log.debug("即将向Spring Security返回UserDetails：{}", userDetails);
//            return userDetails;
//        }
//
//        log.error("没有查询到匹配的管理员信息");
//        return null;
//    }
//
//}
