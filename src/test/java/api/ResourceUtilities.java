package api;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ResourceUtilities {

    //path like "env/bld/requestSchema/hotelbooking_room.json"
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

    //path "src/test/resources/env/bld/requestSchema/hotelbooking_room.json"
    public String fileToString(String filePath) {
        String fileStr = null;

        try {
            fileStr = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileStr;
    }



    public static void main(String[] args) {
        ResourceUtilities ru = new ResourceUtilities();
        String path = "";

        // System.out.println("*************:: "+ru.fileToString("src/test/resources/env/bld/requestSchema/hotelbooking_room.json"));

        System.out.println("%%%%%%%%%%%%%%%%%%%%" + ResourceUtilities.resourceToString("env/bld/requestSchema/hotelbooking_room.json"));
    }
}