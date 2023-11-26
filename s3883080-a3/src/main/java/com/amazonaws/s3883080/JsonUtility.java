package com.amazonaws.s3883080;

import com.amazonaws.services.lambda.runtime.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtility {
    public static Map<String, String> createHeaders(){
        Map<String,String> headers = new HashMap<>();
        headers.put("Content-Type","application/json");
        return headers;
    }

    public static String convertObjToString(Receipt receipt, Context context) {
        String jsonBody = null;
        try {
            jsonBody = new ObjectMapper().writeValueAsString(receipt);
        } catch (JsonProcessingException e) {
            System.out.print(e.getMessage());
        }
        return jsonBody;
    }

    public static Receipt convertStringToObj(String jsonBody, Context context) {
        Receipt receipt = null;
        try {
            receipt = new ObjectMapper().readValue(jsonBody,Receipt.class);
        } catch (JsonProcessingException e) {
            System.out.print(e.getMessage());
        }
        return receipt;
    }

    public static String convertListOfObjToString(List<Receipt> receipts, Context context){
        String jsonBody = null;
        try {
            jsonBody = new ObjectMapper().writeValueAsString(receipts);
        } catch (JsonProcessingException e) {
            System.out.print(e.getMessage());
        }
        return jsonBody;
    }
}
