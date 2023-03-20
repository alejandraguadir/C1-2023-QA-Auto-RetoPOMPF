package com.sofkau.setup;

import org.apache.log4j.PropertyConfigurator;

import static com.google.common.base.StandardSystemProperty.USER_DIR;
import static com.sofkau.util.Log4j.LOG4J_PROPERTIES_FILE_PATH;


public class MarvelHistoriaIdSetup {
    
    protected static String baseURL = "https://gateway.marvel.com/v1/public/";
    protected static String storiesID = "19947";
    protected static String endpoint = "stories/" ;
    protected static String apiKey = "34718a01c54fead5741ff10ad28dd1a4";
    protected static String timestamp = "1";
    protected static String hash = "0f5c4a5cf213cfa76f10a126813546bf";


    protected void generalSetup() {
        setUpLog4j2();
    }

    private void setUpLog4j2() {
        PropertyConfigurator.configure(USER_DIR.value() + LOG4J_PROPERTIES_FILE_PATH.getValue());
    }


}
