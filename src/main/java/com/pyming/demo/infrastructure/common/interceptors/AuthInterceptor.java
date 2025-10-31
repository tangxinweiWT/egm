package com.pyming.demo.infrastructure.common.interceptors;


import com.auth0.jwt.interfaces.Claim;
import com.pyming.demo.infrastructure.common.annotation.Login;
import com.pyming.demo.infrastructure.common.exception.AuthError;
import com.pyming.demo.infrastructure.common.exception.InvalidTokenException;
import com.pyming.demo.infrastructure.utils.JwtTool;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 权限认证拦截器
 */
@Component
@AllArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {
    private final JwtTool jwtTool;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 静态资源地址，直接跳过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        // 控制器调用
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 如果没有Login注解，不验证权限
        Login loginAnnotation = method.getAnnotation(Login.class);
        if (loginAnnotation == null) {
            return true;
        }

        // 获取登录用户
        String bearer = request.getHeader("Authorization");
        if (StringUtils.isEmpty(bearer)) {
            throw new AuthError("未登录");
        }

        Map<String, Claim> identity = jwtTool.verify(bearer);
        Claim memberNo = identity.get("uid");
        if (memberNo == null  || memberNo.isNull()) {
            throw new InvalidTokenException("无效的凭证");
        }
        // 将学工号存入request
        request.setAttribute("uid", memberNo.asString());
        return true;
    }
}
