package com.canyin.canyinbackend.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.Date;

@Configuration
public class JwtToken {
    private static Logger logger = LoggerFactory.getLogger(JwtToken.class);
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expire}")
    private long jwtExpire;

    /**
     * 通过UserID生成JWT
     * @param userId
     * @return
     */
    public String genJWT(Long userId) {
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + jwtExpire * 1000);
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(userId + "")
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    /**
     * 获取Claims
     * @param token
     * @return
     */
    public Claims getClaimByToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }

        String[] header = token.split("Bearer");
        token = header[1];
        try {
            return Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e){
            logger.debug("error ", e);
            return null;
        }
    }

    /**
     * 判断Token是否过期
     * @param expiration
     * @return
     */
    public static boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        JwtToken.logger = logger;
    }

    public String getJwtSecret() {
        return jwtSecret;
    }

    public void setJwtSecret(String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    public long getJwtExpire() {
        return jwtExpire;
    }

    public void setJwtExpire(long jwtExpire) {
        this.jwtExpire = jwtExpire;
    }
}
