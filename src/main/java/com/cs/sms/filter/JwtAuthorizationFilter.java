//package com.cs.sms.filter;
//
//;
//import com.alibaba.fastjson.JSON;
//import com.cs.sms.security.LoginPrincipal;
//import com.cs.sms.util.JwtUtils;
//import com.cs.sms.web.JsonResult;
//import com.cs.sms.web.ServiceCode;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.MalformedJwtException;
//import io.jsonwebtoken.SignatureException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
///**
// * <p>处理JWT的过滤器</p>
// *
// * <p>此过滤器将尝试获取请求中的JWT数据，如果存在有效数据，将尝试解析，</p>
// * <p>然后，将解析得到的结果存入到Spring Security的上下文中，</p>
// * <p>以至于Spring Security框架中的其它组件能够从上下文中获取到用户的信息，</p>
// * <p>从而完成后续的授权访问。</p>
// */
//@Slf4j
//@Component
//public class JwtAuthorizationFilter extends OncePerRequestFilter {
//
//    // 最终，过滤器可以选择“阻止”或“放行”
//    // 如果选择“阻止”，则后续的所有组件都不会被执行
//    // 如果选择“放行”，会执行“过滤器链”中剩余的部分，甚至继续向后执行到控制器等组件
//    @Override
//    protected void doFilterInternal(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            FilterChain filterChain) throws ServletException, IOException {
//        // 此方法是任何请求都会执行的方法
//        log.debug("执行JwtAuthorizationFilter");
//
//        // 清除Security的上下文
//        // 如果不清除，只要此前存入过信息，即使后续不携带JWT，上下文中的登录信息依然存在
//        SecurityContextHolder.clearContext();
//
//        // 从请求头中获取JWT
//        String jwt = request.getHeader("Authorization");
//        log.debug("从请求头中获取的JWT数据：{}", jwt);
//
//        // 先判断是否获取到了有效的JWT数据，如果无JWT数据，直接放行
//        if (!StringUtils.hasText(jwt)) {
//            log.debug("请求头中的JWT数据是无效的，直接放行");
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        // 如果获取到了有效的JWT值，则尝试进行解析
//        Claims claims = null;
//        try {
//            claims = JwtUtils.parse(jwt);
//        } catch (ExpiredJwtException e) {
//            log.debug("解析JWT失败：{}，JWT过期：{}",e.getClass().getName(),e.getMessage());
//            String errorMessage = "登录信息已过期，请重新登录！";
//            JsonResult jsonResult = JsonResult.fail(ServiceCode.ERR_JWT_EXPIRED,errorMessage);
//            String jsonResultString = JSON.toJSONString(jsonResult);
//            log.debug("将向客户端响应：{}",jsonResultString);
//            response.setContentType("application/json;charset=UTF-8");
//            response.getWriter().println(jsonResultString);
//            return;
//        } catch(SignatureException e){
//            log.debug("解析JWT失败，签名错误：{}，{}",e.getClass().getName(),e.getMessage());
//            String errorMessage = "登录信息已过期，请重新登录！";
//            JsonResult jsonResult = JsonResult.fail(ServiceCode.ERR_JWT_EXPIRED,errorMessage);
//            String jsonResultString = JSON.toJSONString(jsonResult);
//            log.debug("将向客户端响应：{}",jsonResultString);
//            response.setContentType("application/json;charset=UTF-8");
//            response.getWriter().println(jsonResultString);
//            return;
//        } catch (MalformedJwtException e){
//            log.debug("解析JWT失败：JWT数据错误{}，{}",e.getClass().getName(),e.getMessage());
//            String errorMessage = "登录信息已过期，请重新登录！";
//            JsonResult jsonResult = JsonResult.fail(ServiceCode.ERR_JWT_INVALID,errorMessage);
//            String jsonResultString = JSON.toJSONString(jsonResult);
//            log.debug("将向客户端响应：{}",jsonResultString);
//            response.setContentType("application/json;charset=UTF-8");
//            response.getWriter().println(jsonResultString);
//            return;
//        } catch (Throwable e){
//            log.debug("解析JWT失败：{}，{}",e.getClass().getName(),e.getMessage());
//            String errorMessage = "登录信息已过期，请重新登录！";
//            JsonResult jsonResult = JsonResult.fail(ServiceCode.ERR_JWT_INVALID,errorMessage);
//            String jsonResultString = JSON.toJSONString(jsonResult);
//            log.debug("将向客户端响应：{}",jsonResultString);
//            response.setContentType("application/json;charset=UTF-8");
//            response.getWriter().println(jsonResultString);
//            return;
//        }
//
//        //从解析JWT中的结果中取出登录的管理员的信息
//        Object id = claims.get("id");
//        log.debug("从JWT中解析得到Id:{}",id);
//        Object username = claims.get("username");
//        log.debug("从JWT中解析得到用户名：{}", username);
//        Object authoritiesString = claims.get("authorities");
//        log.debug("从JWT中解析得到权限列表的字符串：{}", authoritiesString);
//
////        // TODO 临时：准备用户权限
////        GrantedAuthority authority = new SimpleGrantedAuthority("1");//前面为接口，后面为该接口的实现类
////        List<GrantedAuthority> authorities = new ArrayList<>();
////        authorities.add(authority);
//
//        //将从JWT中解析得到的权限列表的字符串封装为GrantedAuthority集合
//        List<SimpleGrantedAuthority> authorities
//                = JSON.parseArray(authoritiesString.toString(),SimpleGrantedAuthority.class);
//
//        //准备当前登录用户的
//        LoginPrincipal loginPrincipal = new LoginPrincipal();
//        loginPrincipal.setId(Long.parseLong(id.toString()));
//        loginPrincipal.setUsername(username.toString());
//
//        // 当解析成功后，应该将相关数据存入到Spring Security的上下文中
//        Authentication authentication
//                = new UsernamePasswordAuthenticationToken(loginPrincipal, null, authorities);//封装当事人信息
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        securityContext.setAuthentication(authentication);//核心代码，让security知道已经登录
//        log.debug("已经向Security的上下文中写入：{}", authentication);
//        // 以下代码将执行“放行”
//        filterChain.doFilter(request, response);
//    }
////    http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
//}
