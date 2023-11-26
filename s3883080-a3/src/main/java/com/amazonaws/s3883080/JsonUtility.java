package com.amazonaws.s3883080;

import com.amazonaws.services.lambda.runtime.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class JsonUtility {
    public static Map<String, String> generateHeaders(){
        Map<String,String> generateHeaders = new HashMap<>();
        generateHeaders.put("Content-Type","application/json");
        generateHeaders.put("X-amazon-apiVersion","v1");
        generateHeaders.put("X-amazon-author","peter");
        return generateHeaders;
    }

    public static String convertObjToString(Receipt receipt, Context context) {
        String bodyOfJsonData = null;
        try {
            bodyOfJsonData = new ObjectMapper().writeValueAsString(receipt);
        } catch (JsonProcessingException e) {
            System.out.print(e.getMessage());
        }
        return bodyOfJsonData;
    }
}
