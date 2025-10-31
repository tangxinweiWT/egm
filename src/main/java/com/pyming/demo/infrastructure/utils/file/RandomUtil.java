package com.pyming.demo.infrastructure.utils.file;

import java.util.UUID;


public class RandomUtil {

    /**
     * 生成主键id
     *
     * @return
     */
    public static String uuId() {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23) + uuid.substring(24);
    }
}
