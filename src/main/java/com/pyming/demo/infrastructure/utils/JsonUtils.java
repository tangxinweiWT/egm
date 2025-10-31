package com.pyming.demo.infrastructure.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.pyming.demo.infrastructure.common.exception.AppException;

import java.util.List;

public class JsonUtils {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 将对象转为JSON
     */
    public static String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new AppException(e.getMessage());
        }
    }



    /**
     * 将json转为对象
     */
    public static <T> T getToBean(String json, Class<T> responseType) {
        try {
            return objectMapper.readValue(json, responseType);
        } catch (JsonProcessingException e) {
            throw new AppException(e.getMessage());
        }
    }


    /**
     * 将json转为List
     */
    public static <T> List<T> getToListBean(String json, Class<T> responseType) {
        try {
            TypeFactory typeFactory = objectMapper.getTypeFactory();
            JavaType listType = typeFactory.constructCollectionType(List.class, responseType);
            return objectMapper.readValue(json, listType);
        } catch (JsonProcessingException e) {
            throw new AppException(e.getMessage());
        }
    }

}
