package com.cs.sms.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;

/**
 * JWT工具类
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Slf4j
public final class JwtUtils {

    /**
     * 签名密钥
     */
    private static final String SECRET_KEY = "lkjfdslkjafds8iufnmdsfadsa";
    /**
     * JWT数据有效时间，以分钟为单位
     */
    private static final long EXPIRED_IN_MINUTE = 7 * 24 * 60;

    /**
     * 私有化构造方法，避免外部随意创建对象
     */
    private JwtUtils() {
    }

    /**
     * 生成JWT
     *
     * @param claims 希望在JWT中封装的数据
     * @return JWT字符串
     */
    public static String generate(Map<String, Object> claims) {
        Date expiration = new Date(System.currentTimeMillis() + EXPIRED_IN_MINUTE * 60 * 24 * 7);
        String jwt = Jwts.builder()
                .setHeaderParam("typ", "jwt")
                .setHeaderParam("alg", "HS256")
                .setClaims(claims)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
        log.debug("生成JWT数据：{}", jwt);
        return jwt;
    }

    /**
     * 解析JWT
     *
     * @param jwt JWT数据
     * @return 解析得到的Claims，其中封装了生成JWT时存入的数据
     */
    public static Claims parse(String jwt) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwt).getBody();
    }

}
