package org.tools;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
    /**  
     *   
     */  
    private static final ObjectMapper mapper = new ObjectMapper();   
  
    /**  
     *   
     */  
    private JsonUtils() {   
  
    }   
  
    /**  
     *   
     * @return  
     */  
    public static ObjectMapper getInstance() {   
        return mapper;   
    }   
}
