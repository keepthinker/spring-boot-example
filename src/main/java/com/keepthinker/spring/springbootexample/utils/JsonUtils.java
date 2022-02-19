package com.keepthinker.spring.springbootexample.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class JsonUtils {
    private static Logger logger = LoggerFactory.getLogger(JsonUtils.class);
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> List<T> stringToList(String value) {
        try {
            return objectMapper.readValue(value,  new TypeReference<List<T>>(){});
        } catch (JsonProcessingException e) {
            logger.error("failed to jsonStringToList", e);
            return null;
        }
    }

    public static String objectToString(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("failed to objectToJsonString", e);
            return null;
        }
    }
}
