//package com.cs.sms.config;
//
//
//import com.cs.sms.filter.JwtAuthorizationFilter;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Slf4j
//@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private JwtAuthorizationFilter jwtAuthorizationFilter;
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        log.debug("创建密码编码器组件：BCryptPasswordEncoder");
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    @Override
//    protected AuthenticationManager authenticationManager() throws Exception {
//        return super.authenticationManager();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // 在配置路径时，可以使用星号作为通配符
//        // 使用 /* 只能匹配1层级路径，例如 /user 或 /brand，不可以匹配多层级，例如不可以匹配到 /user/list
//        // 使用 /** 可以匹配若干层级路径
//
//        // 白名单，不需要登录就可以访问
//        String[] urls = {
//                "/users/exportExcel",//放行导出用户列表
//                "/users/login",
//                "/doc.html",
//                "/**/*.css",
//                "/**/*.js",
//                "/favicon.ico",
//                "/v2/api-docs",
//                "/swagger-resources"
//        };
////        http.cors();
////        http.csrf().disable(); // 禁用防止跨域访问，如果无此配置，白名单路径的异步访问也会出现403错误
////
////        http.authorizeRequests() // 请求需要被授权才可以访问
////                .antMatchers(urls) // 匹配某些路径
////                .permitAll() // 允许直接访问（不需要经过认证和授权）
////                .anyRequest() // 除了以上配置过的其它任何请求
////                .authenticated(); // 已经通过认证，即已经登录过才可以访问
////
////        // 添加处理JWT的过滤器，必须执行在处理用户名、密码的过滤器（Spring Security内置）之前
////        http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
//
//        http.cors();
//        http.csrf().disable(); // 禁用防止跨域访问，如果无此配置，白名单路径的异步访问也会出现403错误
//
//        http.authorizeRequests() // 请求需要被授权才可以访问
//                //.antMatchers(HttpMethod.OPTIONS, "/**") //全部放行
//                //.permitAll()
//
//
//                .antMatchers(urls)//匹配某些路径
//                .permitAll() // 允许直接访问（不需要经过认证和授权）
//                .anyRequest()
//                .authenticated();
//
//        http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
//    }
//
////    public static void main(String[] args) {
////        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
////        String encode = bCryptPasswordEncoder.encode("123456");
////        System.out.println(encode);
////    }  //生成密码
//
//}
//
//
//
