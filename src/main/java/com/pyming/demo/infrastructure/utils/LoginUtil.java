package com.pyming.demo.infrastructure.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class LoginUtil {
    private final JwtTool jwtTool;


    public String generateToken(String uid) {
        Map<String, String> signData = new HashMap<>();
        signData.put("uid", uid);
        return jwtTool.createToken(signData);
    }
}
