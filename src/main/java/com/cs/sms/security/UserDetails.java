//package com.cs.sms.security;
//
//import lombok.EqualsAndHashCode;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//
//import java.util.Collection;
//
///**
// * 自定义的UserDetails实现类，用于对Security内置的User类型进行扩展
// *
// * @author java@tedu.cn
// * @version 0.0.1
// */
//@Setter
//@Getter
//@EqualsAndHashCode
//@ToString(callSuper = true)
//public class UserDetails extends User {
//
//    /**
//     * 管理员id
//     */
//    private Long id;
//
//    public UserDetails(String username, String password, boolean enabled,
//                       Collection<? extends GrantedAuthority> authorities) {
//        super(username, password, enabled,
//                true, true, true,
//                authorities);
//    }
//
//}
