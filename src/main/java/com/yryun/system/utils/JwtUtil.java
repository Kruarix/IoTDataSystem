package com.yryun.system.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

/**
 * JWT工具类，用于生成和解析JWT令牌
 */
@Slf4j
@Component
public class JwtUtil {

    // 生成安全的密钥
    public static final Long JWT_TTL = 60 * 60 * 1000L; // JWT有效期为1小时，以毫秒为单位
    public static final Key JWT_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256); // 随机生成的秘钥

    // 获取随机生成的UUID
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 生成JWT令牌（默认有效期1小时）
     *
     * @param subject token中要存放的数据（json格式）
     * @return 生成的JWT令牌
     */
    public static String createJWT(String subject) {
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID());
        return builder.compact();
    }

    /**
     * 生成JWT令牌
     *
     * @param subject   token中要存放的数据（json格式）
     * @param ttlMillis token超时时间
     * @return 生成的JWT令牌
     */
    public static String createJWT(String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID());
        return builder.compact();
    }

    /**
     * 生成JWT令牌
     *
     * @param id        唯一的ID
     * @param subject   token中要存放的数据（json格式）
     * @param ttlMillis token超时时间
     * @return 生成的JWT令牌
     */
    public static String createJWT(String id, String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id);
        return builder.compact();
    }

    // 创建JwtBuilder对象，设置JWT的各种信息
    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        long nowMillis = System.currentTimeMillis();    // 获取当前时间的毫秒数
        Date now = new Date(nowMillis);         // 当前时间
        if (ttlMillis == null) {
            ttlMillis = JwtUtil.JWT_TTL;        // 如果未传入超时时间，则使用默认的JWT有效期
        }
        long expMillis = nowMillis + ttlMillis; // JWT的过期时间毫秒数
        Date expDate = new Date(expMillis);     // JWT的过期时间
        return Jwts.builder()
                .setId(uuid)                    // 设置JWT的唯一ID
                .setSubject(subject)            // 设置JWT的主题，可以是JSON数据
                .setIssuer("signer")            // 设置JWT的签发者
                .setIssuedAt(now)               // 设置JWT的签发时间
                .signWith(JWT_KEY)              // 使用秘钥进行签名
                .setExpiration(expDate);        // 设置JWT的过期时间
    }

    /**
     * 解析JWT令牌
     *
     * @param jwt JWT令牌
     * @return 解析出的Claims
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception {
        return Jwts.parserBuilder()
                .setSigningKey(JWT_KEY)         // 使用秘钥进行解析
                .build()
                .parseClaimsJws(jwt)
                .getBody();                     // 获取JWT中的Claims（数据）
    }

}
