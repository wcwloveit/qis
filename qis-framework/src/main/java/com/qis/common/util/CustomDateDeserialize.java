package com.qis.common.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Date;

public class CustomDateDeserialize extends JsonDeserializer<Date> {  
	   
    @Override  
    public Date deserialize(JsonParser jp, DeserializationContext ctxt)  
            throws IOException, JsonProcessingException {  
  
        Date date = null;  
        try {  
            date = DateUtils.parseDate(jp.getText());  
        } catch (JsonProcessingException e) {  
            e.printStackTrace();  
        }  
        return date;  
    }  
}   