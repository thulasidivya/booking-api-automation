package com.qe.vt.api.framework;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpHeaders;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class ResourceUtilities {

    //path like "env/bld/requestSchema/post-bookingRoom.json"
    public static String resourceToString(String filePath) {
        try (InputStream inputStream = ResourceUtilities.class.getClassLoader().getResourceAsStream(filePath)) {
            return inputStreamToString(inputStream);
        } catch (IOException e) {
            System.out.println("Exception message" + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    private static String inputStreamToString(InputStream inputStream) {
        try (Scanner scanner = new Scanner(inputStream).useDelimiter("\\A")) {
            return scanner.hasNext() ? scanner.next() : "";
        }
    }

    //path "src/test/resources/env/bld/requestSchema/post-bookingRoom.json"
    public String fileToString(String filePath) {
        String fileStr = null;

        try {
            fileStr = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileStr;
    }

    public static Map<String, String> getDefaultRequestHeaders() {
        Map<String, String> reqHeaderMap = new HashMap<>();

        reqHeaderMap.put(HttpHeaders.CONTENT_TYPE, "application/json");
        reqHeaderMap.put(HttpHeaders.ACCEPT, "*/*");
        reqHeaderMap.put("x_correlation_id", UUID.randomUUID().toString());
        reqHeaderMap.put("x_channel", "APP");
        reqHeaderMap.put(HttpHeaders.AUTHORIZATION, "VALID_STATIC_TOKEN");
        reqHeaderMap.put("x_user_id", "admin");
        return reqHeaderMap;
    }


    public static String updateRequestFieldsValues(String reqBody, String key, String value){

        JsonNode rootNode = null;
        try {
            rootNode = new ObjectMapper().readTree(reqBody);

            if(rootNode.has(key)) {
                ((com.fasterxml.jackson.databind.node.ObjectNode) rootNode).put(key, value);

            } else if (rootNode.isObject()){

                String[] keyArray = key.split("\\.");
                int keyArrayLength= keyArray.length-1;

                JsonNode childNode = rootNode;
                for(String nodeKey: keyArray){
                    if(nodeKey.equals(keyArray[keyArrayLength])){
                        break;
                    }
                    childNode =   childNode.get(nodeKey);

                }
                ((com.fasterxml.jackson.databind.node.ObjectNode) childNode).put(keyArray[keyArrayLength], value);

            }else if (rootNode.isArray()){
                System.out.println("________________________ isArray ");
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return rootNode.toPrettyString();
    }


    public static String removeRequestField(String reqBody, String key){

        JsonNode rootNode = null;
        try {
            rootNode = new ObjectMapper().readTree(reqBody);

            if(rootNode.has(key)) {
                ((com.fasterxml.jackson.databind.node.ObjectNode) rootNode).remove(key);

            } else if (rootNode.isObject()){

                String[] keyArray = key.split("\\.");
                int keyArrayLength= keyArray.length-1;

                JsonNode childNode = rootNode;
                for(String nodeKey: keyArray){
                    if(nodeKey.equals(keyArray[keyArrayLength])){
                        break;
                    }
                    childNode =   childNode.get(nodeKey);

                }
                ((com.fasterxml.jackson.databind.node.ObjectNode) childNode).remove(keyArray[keyArrayLength]);

            }else if (rootNode.isArray()){
                System.out.println("________________________ isArray ");
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return rootNode.toPrettyString();
    }

}
