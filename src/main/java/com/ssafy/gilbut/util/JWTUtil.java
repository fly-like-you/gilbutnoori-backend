package com.ssafy.gilbut.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JWTUtil {

    @Value("${jwt.access-token.expiretime}")
    private int accessTokenExpireTime;

    @Value("${jwt.refresh-token.expiretime}")
    private int refreshTokenExpireTime;

    @Value("${jwt.secret-key}")
    private String secretKey;

    public String createAccessToken(String userId) {
        return create(userId, "access-token", accessTokenExpireTime);
    }

    //	AccessToken에 비해 유효기간을 길게 설정.
    public String createRefreshToken(String userId) {
        return create(userId, "refresh-token", refreshTokenExpireTime);
    }

    //	Token 발급
    private String create(String loginId, String subject, long expireTime) {
        SecretKey key = getSecretKey();
        Map<String, String> headers = new HashMap<>();
        headers.put("typ", "JWT");

        String jwt = Jwts.builder()
                .header()
                .add(headers)
                .and()
                .subject(subject)
                .claim("loginId", loginId)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(key, Jwts.SIG.HS256)
                .compact();

        return jwt;
    }

    private SecretKey getSecretKey() {
        byte[] secretKeyBytes = Base64.getEncoder().encode(secretKey.getBytes());
        return Keys.hmacShaKeyFor(secretKeyBytes);
    }


    public boolean checkToken(String token) {
        SecretKey key = getSecretKey();

        var parser = Jwts.parser()
                .verifyWith(key)
                .build();
        var result = parser.parseSignedClaims(token);
        result.getPayload().forEach((key1, value1) -> log.info("key : {}, value : {}", key1, value1));

        return true;
    }

    public String getUserId(String authorization) {
        Jws<Claims> claims = null;
        var parser = Jwts.parser()
                .verifyWith(getSecretKey())
                .build();
        claims = parser.parseSignedClaims(authorization);

        Map<String, Object> value = claims.getPayload();
        log.info("value : {}", value);

        return value.get("userId").toString();
    }
    //	전달 받은 토큰이 제대로 생성된 것인지 확인 하고 문제가 있다면 UnauthorizedException 발생.

}
