package com.pyming.demo.infrastructure.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class ConfigValueUtil {
    @Value("${config.path}")
    private String path;


    /**
     * 获取资源绝对路径
     */
    public String getAssetPathAbs() {
        return path;
    }
}
