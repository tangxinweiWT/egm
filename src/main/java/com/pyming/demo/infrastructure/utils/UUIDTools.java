package com.pyming.demo.infrastructure.utils;

import java.util.UUID;

public class UUIDTools {
    public static String uuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }
}
