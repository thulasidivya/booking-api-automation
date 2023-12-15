package com.qe.vt.api.utilities;

import com.qe.vt.api.constants.Config;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {
    private static PropertyManager instance;
    private static final Object lock= new Object();
    //private static final String propertyFilePath=System.getProperty("user.dir")+"\\scr\\test\\resources\\env\\bld\\configuration.properties";
        private static Properties configurationProp;
        private static Properties testDataProp;
    public static PropertyManager getInstance() {
        if(instance==null){
            synchronized (lock){
                instance=new PropertyManager();
                instance.loadConfigData();
                instance.loadTestData();
            }
        }
        return instance;
    }
    private String getEnvName(String fileName){
        String propFilePath = "./src/test/resources/env/"+ Config.ENV_NAME+"/"+fileName+".properties";
        //System.getProperty("user.dir")+"/scr/test/resources/env/"+ Config.ENV_NAME+"/"+fileName+".properties";

       // String propFilePath = "D:\\Thulasi\\orange-hrm-automation-framework\\src\\test\\resources\\env\\bld\\testdata.properties";
        System.out.println(fileName+">>propFilePath::" +propFilePath);
        return propFilePath;

    }
    private void loadConfigData(){
        configurationProp=new Properties();
        try{
            System.out.println("loading configuration.properties::");
            configurationProp.load(new FileInputStream(getEnvName("configuration")));
          //  prop.load(this.getClass().getClassLoader().getResourceAsStream("configuration.properties"));

        }catch (IOException e){
            System.out.println("Configuration properties file cannot be found");
            e.printStackTrace();
        }
    }
    private void loadTestData(){
         testDataProp=new Properties();
        try{
            System.out.println("loading configuration.properties::");

            testDataProp.load(new FileInputStream(getEnvName("testdata")));
            //  prop.load(this.getClass().getClassLoader().getResourceAsStream("configuration.properties"));

        }catch (IOException e){
            System.out.println("testdata properties file cannot be found");
        }
    }
    public String getConfigPropByName(String propName) {return configurationProp.getProperty(propName);
    }

        public String getTestDataPropByName(String propName){ return testDataProp.getProperty(propName);
    }

    public static void main(String[] args) {
        PropertyManager
        PropUtility = PropertyManager.getInstance();
        Logger logger = null;
        logger.atDebug().log("Property intput....."+PropUtility.getTestDataPropByName("login.user.name"));

    }
}
