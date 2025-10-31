package com.pyming.demo.infrastructure.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import com.pyming.demo.infrastructure.common.exception.InvalidTokenException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Data
public class JwtTool {
    @Value("${jwt.secret}")
    private String secret;
    // Token 有效期
    @Value("${jwt.expiredTime}")
    private int expiredTime;

    public String createToken(Map<String, String> signData) {
        Algorithm algorithm = Algorithm.HMAC256(this.secret);

        // 签名头
        Map<String, Object> signHeader = new HashMap<String, Object>();
        signHeader.put("alg", "HS256");
        signHeader.put("typ", "JWT");
        JWTCreator.Builder builder = JWT.create();
        builder.withHeader(signHeader);

        // 签名过期时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, expiredTime);
        builder.withExpiresAt(calendar.getTime());
        // 设置加密的数据
        for (String key : signData.keySet()) {
            builder.withClaim(key, signData.get(key));
        }
        return builder.sign(algorithm);
    }

    /**
     * 验证Token是否过期
     *
     * @param token Token令牌
     * @return claims，如果验签失败，则抛出JWTVerificationException. 过期，抛出TokenExpiredException
     */
    public Map<String, Claim> verify(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.replaceAll("^Bearer ", "");
        }

        Algorithm algorithm = Algorithm.HMAC256(this.secret);
        Verification verifier = JWT.require(algorithm);
        DecodedJWT jwt;
        try {
            jwt = verifier.build().verify(token);
        } catch (Exception e) {
            throw new InvalidTokenException("登录已过期");
        }

        Map<String, Claim> claimMap = jwt.getClaims();
        Date expiredDate = jwt.getExpiresAt();
        Date currentDate = new Date();

        if (expiredDate.getTime() <= currentDate.getTime()) {
            throw new InvalidTokenException("登录已过期");
        }

        return claimMap;
    }
}
